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
@ZenClass("mods.Hileb.lang_magic_tweaker.LangMagicTweaker")
@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
public class LangMagicTweaker{
    
    public static final Map<ResourceLocation, LangMagic> LANGS = new HashMap<>();

    @ZenMethod
    public static void unregister(String name) {
        LANGS.remove(new ResourceLocation(name));
    }

    @ZenMethod
    public static LangMagic get(String name) {
        return LANGS.get(new ResourceLocation(name));
    }

    @ZenMethod
    public static void register(String name, LangMagic lang) {
        LANGS.put(new ResourceLocation(name), lang);
    }

    @ZenMethod
    public static void registerWord(String name, String word, LangMagicFunction function) {
        register(name, new LangMagic(context -> word.equals(context.getMessage()), function));
    }

    @ZenMethod
    public static void registerKeyword(String name, String word, LangMagicFunction function) {
        register(name, new LangMagic(context -> context.getMessage().contains(word), function));
    }

    @ZenMethod
    public static void registerRegex(String name, String regex, LangMagicFunction function) {
        register(name, new LangMagic(context -> stringMatches(regex, context.getMessage()), function));
    }

    @ZenMethod
    public static LangMagic[] getAll(){
        return LANGS.values().toArray(new LangMagic[0]);
    }

    @ZenMethod
    public static boolean stringMatches(String reg, String msg){
        return Pattern.compile(reg).matcher(msg).find();
    }

    public static void handle(ServerChatEvent event) {
        LangMagicContext context = new LangMagicContext(event);
        for (LangMagic lang : LANGS.values()) {
            if (lang.getPredicate().test(context)) {
                lang.getFunction().exec(context);
            }
        }
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
