```zencode
#loader lang_magic_tweaker
import mods.Hileb.lang_magic_tweaker.LangMagicTweaker;
import mods.Hileb.lang_magic_tweaker.LangMagicTweaker$MagicLangContext;
MagicLangPredicate;

//Add for Word
LangMagicTweaker.registerWord("hileb:word", "hileb", function(context as LangMagicTweaker$MagicLangContext){
    context.getPlayer().sendChat("Hello! I am Hileb!!!");
});

//Add for Keyword
LangMagicTweaker.registerKeyword("hileb:rain", "my gold!", function(context as LangMagicTweaker$MagicLangContext){
    context.getPlayer().give(<minecraft:gold_ingot>);
});

//Add for regex
LangMagicTweaker.registerRegex("hileb:diamond", "(?<![^\s_])diamond(?![^\s_])", function(context as LangMagicTweaker$MagicLangContext){
    context.getPlayer().give(<minecraft:diamond>);
});

LangMagicTweaker.unregister("yanling:fool"); // "屁屁墨"

for lang in LangMagicTweaker.getAll() {
	LangMagicTweaker$MagicLangPredicate p = lang.getPredicate();
    lang.setPredicate(function(context as LangMagicTweaker$MagicLangContext){
        return context.getPlayer().xp > 100 && p.test(context);
    } as bool)
}
```