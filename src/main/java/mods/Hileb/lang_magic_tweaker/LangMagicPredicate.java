package mods.Hileb.lang_magic_tweaker;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenConstructor;
import crafttweaker.annotations.ZenRegister;

@ZenRegister
@ZenClass("mods.Hileb.lang_magic_tweaker.LangMagicPredicate")
@FunctionalInterface
public interface LangMagicPredicate {
    @ZenMethod
    boolean test(LangMagicContext context);
}