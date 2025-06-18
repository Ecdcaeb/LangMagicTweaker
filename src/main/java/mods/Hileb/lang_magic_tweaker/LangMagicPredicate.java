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

    @ZenMethod
    public static LangMagicPredicate ofWord(String word){
        return context -> word.equals(context.getMessage());
    }

    @ZenMethod
    public static LangMagicPredicate ofKeywork(String word){
        return context -> context.getMessage().contains(word);
    }

    @ZenMethod
    public static LangMagicPredicate ofRegex(String word){
        return context -> LangMagicTweaker.stringMatches(regex, context.getMessage());
    }
}