package mods.Hileb.lang_magic_tweaker.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import mods.Hileb.lang_magic_tweaker.LangMagicTweaker;

@Mixin(com.MIE.Language_arts.core.ChatEventExecute.class)
public abstract class MixinChatEventExecute {

    @Inject(method = "Chat", at = @At("HEAD"), cancellable = true)
    private static void overwriteChat(net.minecraftforge.event.ServerChatEvent evt, CallbackInfo ci){
        LangMagicTweaker.handle(evt);
        ci.cancel();
    }

}