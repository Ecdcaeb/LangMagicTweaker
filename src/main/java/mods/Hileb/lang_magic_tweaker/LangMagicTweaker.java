package mods.Hileb.lang_magic_tweaker;

import mods.Hileb.lang_magic_tweaker.Tags;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import crafttweaker.CraftTweakerAPI;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenConstructor;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.player.IPlayer;

import net.minecraftforge.event.ServerChatEvent;

import zone.rong.mixinbooter.ILateMixinLoader;
import java.util.*;
import java.util.function.*;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.ModMetadata;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@ZenRegister
@ZenClass
@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
public class LangMagicTweaker implements ILateMixinLoader {

    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        CraftTweakerAPI.tweaker.loadScript(false, Tags.MOD_ID);
    }

    @Override
    public List<String> getMixinConfigs() {
        List<String> str = new ArrayList<>();
        str.add("mixins.lang_magic_tweaker.json");
        return str;
    }

    
    public static final Map<ResourceLocation, MagicLang> LANGS = new HashMap<>();

    @ZenMethod
    public static void unregister(String name) {
        LANGS.remove(new ResourceLocation(name));
    }

    @ZenMethod
    public static MagicLang get(String name) {
        return LANGS.get(new ResourceLocation(name));
    }

    @ZenMethod
    public static void register(String name, MagicLang lang) {
        LANGS.put(new ResourceLocation(name), lang);
    }

    @ZenMethod
    public static void registerWord(String name, String word, MagicLangFunction function) {
        register(name, new MagicLang(context -> word.equals(context.getMessage()), function));
    }

    @ZenMethod
    public static void registerKeyword(String name, String word, MagicLangFunction function) {
        register(name, new MagicLang(context -> context.getMessage().contains(word), function));
    }

    @ZenMethod
    public static void registerRegex(String name, String regex, MagicLangFunction function) {
        register(name, new MagicLang(context -> Pattern.matches(regex, context.getMessage()), function));
    }

    @ZenMethod
    public static MagicLang[] getAll(){
        return LANGS.values().toArray(new MagicLang[0]);
    }

    public static void handle(ServerChatEvent event) {
        MagicLangContext context = new MagicLangContext(event);
        for (MagicLang lang : LANGS.values()) {
            if (lang.getPredicate().test(context)) {
                lang.getFunction().exec(context);
            }
        }
    }

    @ZenRegister
    @ZenClass
    public static class MagicLang {
        private MagicLangPredicate predicate;
        private MagicLangFunction function;

        @ZenConstructor
        public MagicLang(MagicLangPredicate predicate, MagicLangFunction function){
            this.predicate = predicate;
            this.function = function;
        }

        @ZenMethod
        public MagicLangFunction getFunction() {
            return function;
        }


        @ZenMethod
        public MagicLangPredicate getPredicate() {
            return predicate;
        }

        @ZenMethod
        public void setFunction(MagicLangFunction function) {
            this.function = function;
        }


        @ZenMethod
        public void setPredicate(MagicLangPredicate predicate) {
            this.predicate = predicate;
        }
    }

    @ZenRegister
    @ZenClass
    public static class MagicLangContext {
        
        private final ServerChatEvent event;
        public MagicLangContext(ServerChatEvent event) {
            this.event = event;
        }

        public ITextComponent getITextComponent() {
            return event.getComponent();
        }

        @ZenMethod
        public crafttweaker.api.text.ITextComponent getComponent() {
            return CraftTweakerMC.getITextComponent(event.getComponent());
        }


        @ZenMethod
        public String getMessage() {
            return event.getMessage();
        }


        @ZenMethod
        public String getUsername() {
            return event.getUsername();
        }

        public EntityPlayerMP getEntityPlayer() {
            return event.getPlayer();
        }

        @ZenMethod
        public IPlayer getPlayer() {
            return CraftTweakerMC.getIPlayer(event.getPlayer());
        }
    }

    @ZenRegister
    @ZenClass
    @FunctionalInterface
    public static interface MagicLangPredicate {
        @ZenMethod
        boolean test(MagicLangContext context);
    }

    @ZenRegister
    @ZenClass
    @FunctionalInterface
    public static interface MagicLangFunction {
        @ZenMethod
        void exec(MagicLangContext context);
    }

    static {
        registerKeyword("yanling:fool","屁屁墨", (context -> {
            context.getEntityPlayer().sendStatusMessage(new TextComponentTranslation("大傻蛋", new Object[10]), true);
        }));
        registerKeyword("yanling:score", "红寡妇", context -> {
            context.getEntityPlayer().sendStatusMessage(new TextComponentTranslation("木寡妇，好消息", new Object[10]), false);
            context.getEntityPlayer().sendStatusMessage(new TextComponentTranslation("高考成绩下来了", new Object[10]), false);
            context.getEntityPlayer().sendStatusMessage(new TextComponentTranslation("你猜怎么着", new Object[10]), false);
            context.getEntityPlayer().sendStatusMessage(new TextComponentTranslation("你儿子给给高考满分！", new Object[10]), false);
        });
        registerKeyword("yanling:fly","悬浮术", (context -> {
            context.getEntityPlayer().addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 100, 1, true, true));
        }));
        registerKeyword("yanling:ray", "悬浮射线", context -> {
            com.MIE.Language_arts.magic.Suspended_ray.susp(context.getEntityPlayer());
        });
        registerKeyword("yanling:heal", "木灵回春术", context -> {
            context.getEntityPlayer().addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 500, 3, true, true));
            context.getEntityPlayer().addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 500, 3, true, true));
        });
        registerKeyword("yanling:force", "牛魔大力功", context -> {
            context.getEntityPlayer().addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 500, 10, true, true));
            context.getEntityPlayer().addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 500, 5, true, true));
        });
        registerKeyword("yanling:catch", "捕捉", context -> {
            com.MIE.Language_arts.magic.Seize.seize(context.getEntityPlayer());
        });
        registerKeyword("yanling:thunder", "雷击术", context -> {
            com.MIE.Language_arts.magic.Thunder.thun(context.getEntityPlayer());
        });
        registerKeyword("yanling:strengthen", "强化术", context -> {
            context.getEntityPlayer().addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 500, 5, true, true));
            context.getEntityPlayer().addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 500, 5, true, true));
            context.getEntityPlayer().addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 500, 5, true, true));
        });
        registerKeyword("yanling:thunderstorm", "大雷暴", context -> {
            com.MIE.Language_arts.magic.Thunderstorm.thunder(context.getEntityPlayer());
        });
        registerKeyword("yanling:arrow","射箭", context -> {
            Items.BOW.onPlayerStoppedUsing(new ItemStack(Items.BOW), context.getEntityPlayer().world, context.getEntityPlayer(), 10000);
        });
        registerKeyword("yanling:sky","万象天引", context -> {
            com.MIE.Language_arts.magic.Gravitation.gravitation(context.getEntityPlayer());
        });
    }

}
