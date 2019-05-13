package projeto.utils;

import java.util.HashMap;
import java.util.Map;

public enum GenderSituation {
    Default(0, "Default"),Feminino(1, "Feminino"), Masculino(2, "Masculino"),Intersexual(3, "Intersexual") , Transexual(4, "Transexual"), NaoBinario(5, "Não Binário") ;

    public int code;
    public String description;
    private static Map<Integer, GenderSituation> genderSituationByCode;

    GenderSituation(int code, String description){
        this.code = code;
        this.description = description;
    }

    static {
        genderSituationByCode = new HashMap<>();
        for (GenderSituation gs: GenderSituation.values()) {
            genderSituationByCode.put(gs.code, gs);
        }
    }

    public static GenderSituation getGenderSituationByCode(int code) {
        return genderSituationByCode.get(code);
    }
}
