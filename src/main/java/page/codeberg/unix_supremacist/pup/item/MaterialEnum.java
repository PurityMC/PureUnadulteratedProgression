package page.codeberg.unix_supremacist.pup.item;

import lombok.Getter;

public enum MaterialEnum {

    Almandine(0xFF0000, Type.dust),
    // Adamantium(0xFFFFFF, Type.metal, Type.dust),
    // Alduorite(0xBFB4B4, Type.metal, Type.dust),
    Aluminium(0x80C8F0, Type.metal, Type.machine, Type.dust, Type.ore),
    AluminiumBrass(0xFFDC14, Type.dust),
    Alumite(0xFF69B4, Type.dust),
    Amber(0xFF8000, Type.gem, Type.dust),
    // Americium(0xC8C8C8, Type.metal, Type.dust),
    Andradite(0x967800, Type.dust),
    Antimony(0xDCDCF0, Type.metal, Type.dust),
    // Angmallen(0xD8E28A, Type.metal, Type.dust),
    Ardite(0xFA8100, Type.dust, Type.ore),
    Ash(0x969696, Type.dust),
    Basalt(0x1E1414, Type.dust),
    BatteryAlloy(0x9C7CA0, Type.metal, Type.dust),
    Bauxite(0x80C8F0, Type.dust, Type.ore),
    // Beryllium(0x64B464, Type.metal, Type.dust),
    Biotite(0x141E14, Type.dust),
    // Bismuth(0x64A0A0, Type.metal, Type.dust),
    BlueAlloy(0x64B4FF, Type.metal, Type.dust),
    // Boron(0xD2FAD2, Type.metal, Type.dust),
    Brass(0xFFB400, Type.metal, Type.machine, Type.dust, Type.tool),
    Bronze(0xFF8000, Type.metal, Type.machine, Type.dust, Type.tool),
    Caesium(0xB4C8DC, Type.dust),
    Calcite(0xFAE6DC, Type.dust),
    Calcium(0xFFF5F5, Type.dust),
    Carbon(0x141414, Type.dust),
    Cadmium(0x32323C, Type.dust, Type.ore),
    Cerium(0x7BD490, Type.dust),
    Charcoal(0x644646, Type.dust),
    Chrome(0xFFE6E6, Type.metal, Type.part, Type.dust, Type.tool),
    Cinnabar(0x960000, Type.dust, Type.ore),
    Clay(0xC8C8DC, Type.dust),
    Coal(0x464646, Type.dust),
    Cobalt(0x5050FF, Type.metal, Type.machine, Type.dust, Type.ore),
    Copper(0xFF6400, Type.metal, Type.machine, Type.dust, Type.tool),
    Cupronickel(0xE39680, Type.metal, Type.dust, Type.coil),
    DarkAsh(0x323232, Type.dust),
    DarkIron(0x37283C, Type.dust, Type.ore),
    Diamond(0xC8FFFF, Type.dust),
    // Draconium(0x7945B0, Type.metal, Type.dust, Type.hot),
    // DraconiumAwakened(0xF44D00, Type.metal, Type.dust),
    // Dysprosium(0x69D250, Type.metal, Type.dust),
    Electrum(0xFFFF64, Type.metal, Type.part, Type.dust, Type.tool),
    Emerald(0x50FF50, Type.dust),
    EnderEye(0xA0FAE6, Type.dust),
    EnderPearl(0x6CDCC8, Type.dust),
    // Erbium(0xB09851, Type.metal, Type.dust, Type.hot),
    // Europium(0xF6B5FF, Type.metal, Type.dust),
    // Gadolinium(0x3BBA1C, Type.metal, Type.dust),
    // Gallium(0xDCDCFF, Type.metal, Type.dust),
    Gold(0xFFFF1E, Type.gold, Type.dust, Type.tool),
    // Holmium(0x1608A6, Type.metal, Type.dust),
    Indium(0x400080, Type.metal, Type.dust, Type.ore),
    Inductive(0xFFC878, Type.metal, Type.dust),
    Invar(0xB4B478, Type.metal, Type.part, Type.dust, Type.tool),
    Iridium(0xF0F0F5, Type.metal, Type.dust, Type.ore),
    Iron(0xB8B8B8, Type.iron, Type.part, Type.dust, Type.tool),
    Kanthal(0xB4B478, Type.metal, Type.dust, Type.coil, Type.hot),
    // Lanthanum(0x8A8A8A, Type.metal, Type.dust),
    Lead(0x544773, Type.metal, Type.dust, Type.tool),
    Lithium(0xE2DCFF, Type.dust, Type.ore),
    Lumium(0xFFFF50, Type.metal, Type.dust),
    // LumiumBase(0xBBBB50, Type.metal, Type.dust),
    // Lutetium(0xBD3EC7, Type.metal, Type.dust, Type.hot),
    Magnalium(0xC8BEFF, Type.metal, Type.dust),
    Magnesium(0xFFC8C8, Type.metal, Type.dust),
    Manganese(0xFAFAFA, Type.metal, Type.dust),
    Manyullyn(0x9A4CB9, Type.dustextra),
    // Mercury(0xFFDCDC, Type.metal, Type.dust),
    // Molybdenum(0xB4B4DC, Type.metal, Type.dust),
    // Neodymium(0x646464, Type.metal, Type.dust),
    // Neutronium(0xFAFAFA, Type.metal, Type.dust),
    Nichrome(0xCDCEF6, Type.metal, Type.dust, Type.coil),
    Nickel(0xC8C8FA, Type.metal, Type.machine, Type.dust, Type.tool, Type.ore),
    // Niobium(0xBEB4C8, Type.metal, Type.dust),
    Osmium(0x3232FF, Type.metal, Type.dust, Type.ore),
    // Palladium(0xFFFFC8, Type.metal, Type.dust),
    Phosphorus(0xFFFF00, Type.dust, Type.ore),
    Platinum(0xFFFFC8, Type.metal, Type.machine, Type.dust, Type.tool, Type.ore),
    Plutonium(0xF03232, Type.metal, Type.dust),
    // Potassium(0x96ABDF, Type.metal, Type.dust),
    // Praseodymium(0x76D781, Type.metal, Type.dust),
    // Promethium(0x24B583, Type.metal, Type.dust),
    RedAlloy(0xC80000, Type.metal, Type.dust),
    // Rubidium(0xF01E1E, Type.metal, Type.dust),
    // Samarium(0xFFFFCB, Type.metal, Type.dust),
    // Scandium(0xCBCBCB, Type.metal, Type.dust, Type.hot),
    Signalum(0xFF4000, Type.metal, Type.dust),
    // SignalumBase(0xBB3000, Type.metal, Type.dust),
    Silicon(0x3C3C50, Type.metal, Type.dust),
    Silver(0xDCDCFF, Type.metal, Type.dust, Type.tool),
    Sodium(0x000096, Type.dust, Type.ore),
    Steel(0x808080, Type.metal, Type.machine, Type.dust, Type.tool),
    // Strontium(0xC8C8C8, Type.metal, Type.dust),
    Sulfur(0xC8C800, Type.dust, Type.ore),
    // Tantalum(0x69B7FF, Type.metal, Type.dust),
    // Tellurium(0xC8FF50, Type.metal, Type.dust),
    // Terbium(0xFFFFFF, Type.metal, Type.dust),
    Thorium(0x001E00, Type.metal, Type.dust),
    Thulium(0x596BC2, Type.metal, Type.dust, Type.hot),
    Tin(0xDCDCDC, Type.metal, Type.dust),
    Titanium(0xDCA0F0, Type.metal, Type.machine, Type.dust, Type.tool),
    // Tritanium(0x600000, Type.metal, Type.dust),
    // Tritium(0xFF0000, Type.dust),
    Tungsten(0x323232, Type.metal, Type.part, Type.dust, Type.tool, Type.hot, Type.ore),
    Tungstensteel(0x6464A0, Type.metal, Type.machine, Type.dust, Type.tool, Type.hot),
    Uranium(0x32F032, Type.metal, Type.dust),
    // Vanadium(0x323232, Type.metal, Type.dust),
    // Ytterbium(0x2BC750, Type.metal, Type.dust),
    // Yttrium(0xDCFADC, Type.metal, Type.dust, Type.hot),
    Zinc(0xFAF0F0, Type.metal, Type.dust),

    Tetrahedrite(0xB4713D, Type.dust, Type.ore),

    Pyrite(0xB8B8B8, Type.dust, Type.ore),

    Garnierite(0x464D19, Type.dust, Type.ore),
    Obsidian(0x0, Type.gold),

    Ruby(0xFF6464, Type.gem, Type.dust),
    RedGarnet(0xC85050, Type.gem, Type.dust),

    Sheldonite(0x181b07, Type.dust, Type.ore),
    Enderium(0x5A9187, Type.metal, Type.part, Type.dust, Type.tool),

    AdvancedAlloy(0x87876E, Type.metal, Type.dust, Type.tool),
    Nikolite(0x32C8FF, Type.dust),
    Redstone(0xC80000, Type.redstone),

    Cassiterite(0xDCDCDC, Type.dust, Type.ore),
    WroughtIron(0xDCEBEB, Type.metal, Type.machine, Type.dust, Type.tool),

    Sodalite(0x0, Type.dust, Type.ore),
    Sphalerite(0x0, Type.dust, Type.ore),
    Galena(0xFFFFFF, Type.dust, Type.ore),
    Sapphire(0x0, Type.gem, Type.dust),
    YellowGarnet(0x0, Type.gem, Type.dust),
    Peridot(0x0, Type.gem, Type.dust),

    ;

    @Getter
    public final Material material;

    MaterialEnum(int color, String... parts) {
        material = new Material(this.name(), this.ordinal(), color, parts);
    }

    MaterialEnum(int color, String[]... parts) {
        material = new Material(this.name(), this.ordinal(), color, parts);
    }
}
