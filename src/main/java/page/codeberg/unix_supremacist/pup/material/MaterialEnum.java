package page.codeberg.unix_supremacist.pup.material;

import lombok.Getter;

public enum MaterialEnum {

    copper(0xB4713D, "ingot", "nugget", "plate", "gear", "casing", "hull", "dust", "tiny_dust", "small_dust",
            "hammer_head", "bolt", "rod", "raw_ore", "ring"),
    tetrahedrite(0xB4713D, "dust", "tiny_dust", "small_dust", "raw_ore"),
    iron(0xB8B8B8, "nugget", "plate", "gear", "casing", "hull", "dust", "tiny_dust", "small_dust", "hammer_head",
            "bolt", "rod", "raw_ore", "ring"),
    pyrite(0xB8B8B8, "dust", "tiny_dust", "small_dust", "raw_ore"),
    emerald(0x08dd7b, "dust", "tiny_dust", "small_dust"),
    nickel(0x464D19, "nugget", "plate", "gear", "casing", "hull", "dust", "tiny_dust", "small_dust", "hammer_head",
            "bolt", "rod", "raw_ore", "ring"),
    garnierite(0x464D19, "dust", "tiny_dust", "small_dust", "raw_ore"),
    gold(0xFFFF1E, "plate", "gear", "casing", "hull", "dust", "tiny_dust", "small_dust", "hammer_head", "bolt", "rod",
            "raw_ore", "ring"),
    electrum(0xFFFF64, "ingot", "nugget", "plate", "gear", "casing", "hull", "dust", "tiny_dust", "small_dust",
            "hammer_head", "bolt", "rod", "ring"),
    aluminium(0x80C8F0, "ingot", "nugget", "plate", "gear", "casing", "hull", "dust", "tiny_dust", "small_dust",
            "hammer_head", "bolt", "rod", "raw_ore", "ring"),
    bauxite(0x80C8F0, "dust", "tiny_dust", "small_dust", "raw_ore"),
    chrome(0x80C8F0, "ingot", "nugget", "plate", "gear", "casing", "hull", "dust", "tiny_dust", "small_dust",
            "hammer_head", "bolt", "rod", "raw_ore", "ring"),
    ruby(0xFF6464, "gem", "nugget", "plate", "dust", "tiny_dust", "small_dust"),
    redGarnet(0xC85050, "gem", "nugget", "plate", "dust", "tiny_dust", "small_dust"),
    platinum(0xFFFFC8, "ingot", "nugget", "plate", "gear", "casing", "hull", "dust", "tiny_dust", "small_dust",
            "hammer_head", "bolt", "rod", "raw_ore", "ring"),
    sheldonite(0x181b07, "dust", "tiny_dust", "small_dust", "raw_ore"),
    enderium(0x5A9187, "ingot", "nugget", "plate", "gear", "casing", "hull", "dust", "tiny_dust", "small_dust",
            "hammer_head", "bolt", "rod", "ring"),
    tungsten(0x323232, "ingot", "nugget", "plate", "gear", "casing", "hull", "dust", "tiny_dust", "small_dust",
            "hammer_head", "bolt", "rod", "raw_ore", "ring"),
    bronze(0xFFB400, "ingot", "nugget", "plate", "gear", "casing", "hull", "dust", "tiny_dust", "small_dust",
            "hammer_head", "bolt", "rod", "ring"),
    invar(0xcebe7c, "ingot", "nugget", "plate", "gear", "casing", "hull", "dust", "tiny_dust", "small_dust",
            "hammer_head", "bolt", "rod", "ring"),
    advancedAlloy(0x87876E, "ingot", "nugget", "plate", "gear", "casing", "hull", "dust", "tiny_dust", "small_dust",
            "hammer_head", "bolt", "rod", "ring"),
    nikolite(0x32C8FF, "dust", "tiny_dust", "small_dust"),
    redstone(0xC80000, "tiny_dust", "small_dust"),
    tin(0xDCDCDC, "ingot", "nugget", "plate", "gear", "casing", "hull", "dust", "tiny_dust", "small_dust",
            "hammer_head", "bolt", "rod", "raw_ore", "ring"),
    cassiterite(0xDCDCDC, "dust", "tiny_dust", "small_dust", "raw_ore"),
    wroughtIron(0xDCEBEB, "ingot", "nugget", "plate", "gear", "casing", "hull", "dust", "tiny_dust", "small_dust",
            "hammer_head", "bolt", "rod", "ring"),
    titanium(0xAA8FDE, "ingot", "nugget", "plate", "gear", "casing", "hull", "dust", "tiny_dust", "small_dust",
            "hammer_head", "bolt", "rod", "raw_ore", "ring"),
    tungstensteel(0x6464A0, "ingot", "nugget", "plate", "gear", "casing", "hull", "dust", "tiny_dust", "small_dust",
            "hammer_head", "bolt", "rod", "ring"),
    steel(0x808080, "ingot", "nugget", "plate", "gear", "casing", "hull", "dust", "tiny_dust", "small_dust",
            "hammer_head", "bolt", "rod", "ring"),
    galena(0xFFFFFF, "dust", "tiny_dust", "small_dust", "raw_ore"),
    lead(0x544773, "ingot", "nugget", "plate", "gear", "casing", "hull", "dust", "tiny_dust", "small_dust",
            "hammer_head", "bolt", "rod", "raw_ore", "ring"),
    silver(0x9cbddc, "ingot", "nugget", "plate", "gear", "casing", "hull", "dust", "tiny_dust", "small_dust",
            "hammer_head", "bolt", "rod", "raw_ore", "ring"),;

    @Getter
    public final Material material;

    MaterialEnum(int color, String... parts) {
        material = new Material(this.ordinal(), color, this.name(), parts);
    }
}
