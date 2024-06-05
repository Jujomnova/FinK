package co.edu.uco.fink.crosscutting.helpers;
import static co.edu.uco.fink.crosscutting.helpers.ObjectHelper.getObjectHelper;

public final class TextHelper {

    public static final String EMPTY = "";

    public static final String UNDERLINE = "_";

    private TextHelper(){
        super();
    }

    public static final boolean isNull(final String string){
        return ObjectHelper.getObjectHelper().isNUll(string);
    }

    public static final boolean isNullOrEmpty(final String string){
        return isNull(string) || EMPTY.equals(applyTrim(string));
    }

    public static final String applyTrim(final String string){
        return getDefault(string).trim();
    }

    public static final String getDefault(final String string, final String defaultValue){
        return getObjectHelper().getDefault(string, defaultValue);
    }

    public static final String getDefault(final String string){
        return getDefault(string, EMPTY);
    }

    public static final String concatenate(final String... strings){
        final var sb = new StringBuilder(EMPTY);

        if (!getObjectHelper().isNUll(strings)){
            for (final var string : strings){
                sb.append(applyTrim(string));
            }
        }

        return sb.toString();
    }

    public static final String replaceParams(final String mensaje, final String... params) {
        String result = mensaje;

        for (int i = 0; i < params.length; i++){
            String pattern = "${" + (i+1) + "}";

            result = result.replace(pattern, TextHelper.applyTrim(params[i]));
        }

        return result;
    }

}
