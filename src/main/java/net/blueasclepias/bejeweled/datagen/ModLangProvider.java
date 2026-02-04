package net.blueasclepias.bejeweled.datagen;

import net.blueasclepias.bejeweled.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static net.blueasclepias.bejeweled.Bejeweled.MOD_ID;

/**
 * Provides language translations for the mod.
 */
public class ModLangProvider extends LanguageProvider {

    public ModLangProvider(PackOutput output) {
        super(output, MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("creativetab.bejeweled.jewelry", "Bejeweled");
        add("tooltip.bejeweled.raw_gem", "Can be cut in the Jeweler table");
        add("tooltip.bejeweled.raw_bead", "Can be polished with a Grindstone");

        add("item.minecraft.amethyst_shard", "Rough Amethyst");
        add("item.minecraft.emerald", "Cut Emerald");
        add("item.minecraft.diamond", "Cut Diamond");

        ModItems.ITEMS.getEntries().forEach(item ->
                add(item.get(), formatName(item.getId().getPath()))
        );
    }

    private static final Set<String> LOWERCASE = Set.of("of", "and", "the");

    private static String formatName(String id) {
        return Arrays.stream(id.split("_"))
                .map(s -> LOWERCASE.contains(s)
                        ? s
                        : s.substring(0, 1).toUpperCase() + s.substring(1))
                .collect(Collectors.joining(" "));
    }


}
