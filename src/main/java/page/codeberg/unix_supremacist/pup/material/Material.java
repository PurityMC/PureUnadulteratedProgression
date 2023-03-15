package page.codeberg.unix_supremacist.pup.material;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;

public class Material {

    @Getter
    public int matid;
    @Getter
    public int color;
    @Getter
    public String name;
    @Getter
    public HashSet<String> parts = new HashSet<>();

    public Material(int matid, int color, String name, String... parts) {
        this.matid = matid;
        this.color = color;
        this.name = name;
        this.parts.addAll(Arrays.asList(parts));
    }
}
