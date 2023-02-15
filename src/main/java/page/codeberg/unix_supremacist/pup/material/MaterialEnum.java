package page.codeberg.unix_supremacist.pup.material;

import lombok.Getter;
import static page.codeberg.unix_supremacist.pup.material.PartArrays.*;

public enum MaterialEnum {
    copper(0xB4713D, vanillametal.strings),
    iron(0xB8B8B8, vanillametal.strings),
    gold(0xFFFF1E, vanillametal.strings),
    electrum(0xFFFF64, vanillametal.strings),
    aluminium(0x80C8F0, vanillametal.strings),
    chrome(0x80C8F0, vanillametal.strings),
    ruby(0xFF6464, vanillametal.strings),
    redGarnet(0xC85050, vanillametal.strings),
    platinum(0xFFFFC8, vanillametal.strings),
    enderium(0x5A9187, vanillametal.strings),
    tungsten(0x323232, vanillametal.strings),
    bronze(0xFFB400, vanillametal.strings),
    advancedAlloy(0x87876E, vanillametal.strings),
    nikolite(0x32C8FF, vanillametal.strings),
    redstone(0xC80000, vanillametal.strings),
    tin(0xDCDCDC, vanillametal.strings),
    wroughtIron(0xDCEBEB, vanillametal.strings),
    titanium(0xAA8FDE, vanillametal.strings),
    tungstensteel(0x6464A0, vanillametal.strings),
    steel(0x808080, vanillametal.strings);

    @Getter public final Material material;
    MaterialEnum(int color, String[]... partArrays){
        material = new Material(this.ordinal(), color, this.name(), partArrays);
    }
}
