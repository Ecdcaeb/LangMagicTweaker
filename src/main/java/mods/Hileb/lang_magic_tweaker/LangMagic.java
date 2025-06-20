package mods.Hileb.lang_magic_tweaker;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenConstructor;
import crafttweaker.annotations.ZenRegister;

@ZenRegister
@ZenClass("mods.Hileb.lang_magic_tweaker.LangMagic")
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

    @ZenMethod
    public LangMagic appendPredicate(LangMagicPredicate predicate) {
        this.setPredicate(this.getPredicate().add(predicate));
        return this;
    }

    @ZenMethod
    public LangMagic appendFunction(final LangMagicFunction function) {
        final LangMagicFunction function0 = this.function;
        this.function = (context) -> {
            function0.exec(context);
            function.exec(context);
        };
        return this;
    }
}