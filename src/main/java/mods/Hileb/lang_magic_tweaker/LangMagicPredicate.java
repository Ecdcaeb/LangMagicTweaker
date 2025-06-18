package mods.Hileb.lang_magic_tweaker;

import stanhebben.zenscript.annotations.*;
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
    public static LangMagicPredicate ofKeyword(String word){
        return context -> context.getMessage().contains(word);
    }

    @ZenMethod
    public static LangMagicPredicate ofRegex(String regex){
        return context -> LangMagicTweaker.stringMatches(regex, context.getMessage());
    }

    @ZenMethod
    @ZenOperator(OperatorType.ADD)
    public LangMagicPredicate add(final LangMagicPredicate predicate) {
        return (context) -> this.test(context) && predicate.test(context);
    }


}