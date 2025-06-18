```zencode
#debug

import mods.Hileb.lang_magic_tweaker.LangMagicTweaker;
import mods.Hileb.lang_magic_tweaker.LangMagicContext;
import mods.Hileb.lang_magic_tweaker.LangMagicPredicate;
import crafttweaker.item.IItemStack;
import crafttweaker.oredict.IOreDict;
import mods.Hileb.lang_magic_tweaker.LangMagic;

//Add for Word
LangMagicTweaker.registerWord("hileb:word", "hileb", function(context as LangMagicContext){
    context.getPlayer().sendChat("Hello! I am Hileb!!!");
});

//Add for Keyword
LangMagicTweaker.registerKeyword("hileb:rain", "my gold!", function(context as LangMagicContext){
    context.getPlayer().give(<ore:ingotIron>.firstItem);
});

//Add for regex
LangMagicTweaker.registerRegex("hileb:diamond", "(?<![a-zA-Z])diamond(?![a-zA-Z])", function(context as LangMagicContext){
    context.getPlayer().give(<ore:dirt>.firstItem * 16);
});

LangMagicTweaker.unregister("yanling:fool"); // "屁屁墨"

LangMagicTweaker.register("hileb:xp", LangMagic(function(context as LangMagicContext) as bool {
		return LangMagicTweaker.stringMatches("(?<![a-zA-Z])xp(?![a-zA-Z])", context.getMessage())
			&& context.getPlayer().xp < 10000;
	} , function(context as LangMagicContext){
    		context.getPlayer().xp = context.getPlayer().xp + 100;
	})
);

LangMagicTweaker.get("yanling:catch").setPredicate(LangMagicPredicate.ofKeyword("catch"));

LangMagicTweaker.get("yanling:thunderstorm").appendPredicate(function(context as LangMagicContext) as bool {
		return context.world.isRaining();
});
```
