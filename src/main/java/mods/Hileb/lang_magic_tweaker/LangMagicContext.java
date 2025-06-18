package mods.Hileb.lang_magic_tweaker;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenConstructor;
import crafttweaker.annotations.ZenRegister;

import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.ITextComponent;
import crafttweaker.api.player.IPlayer;
import net.minecraftforge.event.ServerChatEvent;

@ZenRegister
@ZenClass("mods.Hileb.lang_magic_tweaker.LangMagicContext")
public class LangMagicContext {
        
    private final ServerChatEvent event;

    public LangMagicContext(ServerChatEvent event) {
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