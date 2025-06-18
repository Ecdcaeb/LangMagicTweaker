package mods.Hileb.lang_magic_tweaker;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenConstructor;
import crafttweaker.annotations.ZenRegister;

@ZenRegister
@ZenClass
@FunctionalInterface
public interface LangMagicFunction {
    @ZenMethod
    void exec(LangMagicContext context);
}