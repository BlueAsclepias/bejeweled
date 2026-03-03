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
        // Tooltips & Titles
        add("creativetab.bejeweled.bejeweled", "Bejeweled");
        add("tooltip.bejeweled.raw_gemstone", "Can be cut at the Gem Cutting Table");
        add("tooltip.bejeweled.raw_bead", "Can be polished with a Grindstone");
        add("tooltip.bejeweled.processed_gem_category", "Processed Gems");
        add("tooltip.bejeweled.grade", "Grade: ");
        add("tooltip.bejeweled.type", "Type: ");
        add("tooltip.bejeweled.material", "Material: ");
        add("container.bejeweled.gem_cutting_table", "Gem Cutting Table");
        add("container.bejeweled.gem_cutting", "Gem Cutting");

        // Jewel Type
        add("jeweltype.bejeweled.amulet", "Amulet");
        add("jeweltype.bejeweled.bracelet", "Bracelet");
        add("jeweltype.bejeweled.circlet", "Circlet");
        add("jeweltype.bejeweled.ring", "Ring");

        // Jewel Materials
        add("material.bejeweled.copper", "Copper");
        add("material.bejeweled.silver", "Silver");
        add("material.bejeweled.bronze", "Bronze");
        add("material.bejeweled.iron", "Iron");
        add("material.bejeweled.gold", "Gold");
        add("material.bejeweled.steel", "Steel");
        add("material.bejeweled.netherite", "Netherite");

        // Gem Grades
        add("grade.bejeweled.superb", "Superb");
        add("grade.bejeweled.adequate", "Adequate");
        add("grade.bejeweled.basic", "Basic");
        add("grade.bejeweled.crummy", "Crummy");
        add("grade.bejeweled.defective", "Defective");

        add("item.bejeweled.jewel_name", "%s %s %s %s");
        add("item.bejeweled.gem_name", "%s %s");

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
