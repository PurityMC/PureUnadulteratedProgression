package page.codeberg.unix_supremacist.pup.material;

public enum PartArrays {
    vanillametal("ingot", "nugget", "plate", "gear", "casing", "hull", "dust", "tiny_dust", "small_dust", "hammer_head", "bolt", "rod", "raw_ore", "ring");

    String[] strings;
    PartArrays(String... strings){
        this.strings = strings;
    }
}
