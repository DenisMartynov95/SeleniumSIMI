package Asserts;

import java.util.ArrayList;

public class Asserts {
    //Тест №1: Поиск программы из главной страницы
    public static final String NAME_PROGRAM_FDM = "Free Download Manager";

    //Тест №3: переход на сайт MI через лист
    public static final String NAME_MI_PAGE = "Mac";

    //Тест №4: переход на сайт SI через лист
    public static final String NAME_SI_PAGE = "Windows";

    //Тест №7, чек редиректа на страницы категорий

    public static final ArrayList<String> namesPagesAsserts = new ArrayList<>();
    static {
        namesPagesAsserts.add(0,"AI");
        namesPagesAsserts.add(1,"Audio & Video");
        namesPagesAsserts.add(2,"Games");
        namesPagesAsserts.add(3,"System Tools");
        namesPagesAsserts.add(4,"Design & Photo");
        namesPagesAsserts.add(5,"Mobile Phone Utilities");
        namesPagesAsserts.add(6,"Developer Tools");
        namesPagesAsserts.add(7,"Business");
        namesPagesAsserts.add(8,"Internet Tools");
        namesPagesAsserts.add(9,"Education");
        namesPagesAsserts.add(10,"Communication");
        namesPagesAsserts.add(11,"Antivirus & Security");
        namesPagesAsserts.add(12,"Theming");
        namesPagesAsserts.add(13,"Productivity");
        namesPagesAsserts.add(14,"Lifestyle");
        namesPagesAsserts.add(15,"General");
    }

}

