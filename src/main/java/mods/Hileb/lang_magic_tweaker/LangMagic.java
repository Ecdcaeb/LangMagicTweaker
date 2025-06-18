package mods.Hileb.lang_magic_tweaker;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenConstructor;
import crafttweaker.annotations.ZenRegister;

@ZenRegister
@ZenClass
public class LangMagic {
    private LangMagicPredicate predicate;
    private LangMagicFunction function;

    @ZenConstructor
    public LangMagic(LangMagicPredicate predicate, LangMagicFunction function){
        this.predicate = predicate;
        this.function = function;
    }

    @ZenMethod
    public LangMagicFunction getFunction() {
        return function;
    }


    @ZenMethod
    public LangMagicPredicate getPredicate() {
        return predicate;
    }

    @ZenMethod
    public void setFunction(LangMagicFunction function) {
        this.function = function;
    }

    @ZenMethod
    public void setPredicate(LangMagicPredicate predicate) {
        this.predicate = predicate;
    }
}