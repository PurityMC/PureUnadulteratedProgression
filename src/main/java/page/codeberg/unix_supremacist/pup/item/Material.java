package page.codeberg.unix_supremacist.pup.item;

import java.util.Arrays;
import java.util.HashSet;

import lombok.Getter;

public class Material {

    @Getter
    public int matid;
    @Getter
    public int color;
    @Getter
    public String name;
    @Getter
    public HashSet<String> parts = new HashSet<>();

    public Material(String name, int matid, int color, String[]... partArrays) {
        this.matid = matid;
        this.color = color;
        this.name = name;
        for (String[] parts : partArrays) this.parts.addAll(Arrays.asList(parts));
    }
}
