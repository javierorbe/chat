package com.javierorbe.chat.common;

import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public enum Language {

    ALBANIAN("sq"),
    ENGLISH("en"),
    ARABIC("ar"),
    ARMENIAN("hy"),
    AZERBAIJAN("az"),
    AFRIKAANS("af"),
    BASQUE("eu"),
    BELORUSSIAN("be"),
    BULGARIAN("bg"),
    BOSNIAN("bs"),
    WELSH("cy"),
    VIETNAMESE("vi"),
    HUNGARIAN("hu"),
    HAITIAN("ht"),
    GALICIAN("gl"),
    DUTCH("nl"),
    GREEK("el"),
    GEORGIAN("ka"),
    DANISH("da"),
    YIDDISH("he"),
    INDONESIAN("id"),
    IRISH("ga"),
    ITALIAN("it"),
    ICELANDIC("is"),
    SPANISH("es"),
    KAZAKH("kk"),
    CATALAN("ca"),
    KYRGYZ("ky"),
    CHINESE("zh"),
    KOREAN("ko"),
    LATIN("la"),
    LATVIAN("lv"),
    LITHUANIAN("lt"),
    MALAGASY("mg"),
    MALAY("ms"),
    MALTESE("mt"),
    MACEDONIAN("mk"),
    MONGOLIAN("mn"),
    GERMAN("de"),
    NORWEGIAN("no"),
    PERSIAN("fa"),
    POLISH("pl"),
    PORTUGUESE("pt"),
    ROMANIAN("ro"),
    RUSSIAN("ru"),
    SERBIAN("sr"),
    SLOVAKIAN("sk"),
    SLOVENIAN("sl"),
    SWAHILI("sw"),
    TAJIK("tg"),
    THAI("th"),
    TAGALOG("tl"),
    TATAR("tt"),
    TURKISH("tr"),
    UZBEK("uz"),
    UKRAINIAN("uk"),
    FINISH("fi"),
    FRENCH("fr"),
    CROATIAN("hr"),
    CZECH("cs"),
    SWEDISH("sv"),
    ESTONIAN("et"),
    JAPANESE("ja"),
    ;

    private static final Map<String, Language> CODE_MAPPER
            = Arrays.stream(values()).collect(toMap(Language::getCode, e -> e));

    private final String code;

    Language(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }

    public static Optional<Language> fromCode(String code) {
        return Optional.ofNullable(CODE_MAPPER.get(code));
    }
}
