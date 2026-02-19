package net.blueasclepias.bejeweled.datagen;

import net.blueasclepias.bejeweled.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.Arrays;
import java.util.Objects;
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
        add("creativetab.bejeweled.bejeweled", "Bejeweled");
        add("tooltip.bejeweled.raw_gemstone", "Can be cut at the Gem Cutting Table");
        add("tooltip.bejeweled.raw_bead", "Can be polished with a Grindstone");
        add("tooltip.bejeweled.processed_gem_category", "Processed Gems");
        add("tooltip.bejeweled.grade", "Grade: ");
        add("container.bejeweled.gem_cutting_table", "Gem Cutting Table");
        add("container.bejeweled.gem_cutting", "Gem Cutting");

        add("item.minecraft.amethyst_shard", "Raw Amethyst");
        add("block.minecraft.amethyst_block", "Block of Raw Amethyst");
        add("item.minecraft.diamond", "Raw Diamond");
        add("block.minecraft.diamond_block", "Block of Raw Diamond");
        add("item.minecraft.emerald", "Raw Emerald");
        add("block.minecraft.emerald_block", "Block of Raw Emerald");

        ModItems.ITEMS.getEntries().forEach(item ->
                add(item.get(), formatName(Objects.requireNonNull(item.getId()).getPath()))
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
