package com.p92group.zhlobo.controllers.moderator;


import com.p92group.zhlobo.models.Customer;
import com.p92group.zhlobo.models.Seller;
import com.p92group.zhlobo.models.SellerType;
import com.p92group.zhlobo.services.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/moderator")
public class ModeratorController {
    @Autowired
    private SellerService sellerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private CityService cityService;
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String defaultView() {
        return "moderator/index";
    }

    @GetMapping("/add-dev-client")
    public String devClient(){


        return "redirect:/moderator";
    }

    @GetMapping("/add-new-user")
    public void addNewUser() {
            Customer customer = new Customer();
            customer.setPassword("dev2");
            customer.setEmail("dev2");
            customer.setFullname("dev2");
            customer.setPhoneNumber("+dev2");

            customerService.save(customer);
    }

    @GetMapping("/logout")
    public String logoutPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            HttpServletRequest request = null;
            HttpServletResponse response = null;
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }


    @GetMapping("/add-example-data")
    public String addExampleData() {
        {
            Customer customer = new Customer();
            customer.setPassword("dev");
            customer.setEmail("dev");
            customer.setFullname("dev");
            customer.setPhoneNumber("+dev");

            customerService.save(customer);
        }

        var ps = productService;

        sellerService.saveWithProducts(
                new Seller(
                        "Абат Торгашевич",
                        "atb.contact@grocery.com",
                        "987-654-3210",
                        "password",
                        "вул. Квіткова, 89",
                        "АТБ",
                        imageService.smartSave("https://res.cloudinary.com/glovoapp/q_30,f_auto,c_fill,h_250,w_450/Stores/axyk5zynwbjjys3gp9t6"),
                        SellerType.Grocery,
                        cityService.smartSave("Житомир")
                )
                ,
                Arrays.asList(
                        ps.smartCreate("Чіпси 125г Люкс зі смаком лосося у вершках", "55,60", "https://src.zakaz.atbmarket.com/origin/photos/47295.jpg"),
                        ps.smartCreate("Мигдаль 150г Almond смажений солений м/уп", "99,90", "https://src.zakaz.atbmarket.com/origin/photos/33239.jpg"),
                        ps.smartCreate("Картопляна соломка 75г Своя Лінія зі смаком сметани із зеленню", "29,90", "https://src.zakaz.atbmarket.com/origin/photos/2882.jpg"),
                        ps.smartCreate("Насіння гарбузове 80 г Сан Санич смажене солоне м/уп", "30,30", "https://src.zakaz.atbmarket.com/origin/photos/33819.jpg"),
                        ps.smartCreate("Чипси 100 г Своя Лінія картопляні зі смаком барбекю", "42,80", "https://src.zakaz.atbmarket.com/origin/photos/14252.jpg"),
                        ps.smartCreate("Чипси 125 г Люкс зі смаком лисичок м/уп", "55,60", "https://src.zakaz.atbmarket.com/origin/photos/31211.jpg"),
                        ps.smartCreate("Снеки 140г Своя лінія з пелет пшенично-картопляні зі смаком бекону", "35,70", "https://src.zakaz.atbmarket.com/origin/photos/1304.jpg"),
                        ps.smartCreate("Чипси 125г Lay's Baked картопляні запечені зі смаком лисичок у сметані м/уп", "68,30", "https://src.zakaz.atbmarket.com/origin/photos/36179.jpg"),
                        ps.smartCreate("Картопляна соломка 75г Своя Лінія зі смаком сиру", "29,90", "https://src.zakaz.atbmarket.com/origin/photos/2736.jpg"),
                        ps.smartCreate("Арахіс 50г Своя Лінія смажений солоний", "11,50", "https://src.zakaz.atbmarket.com/origin/photos/2918.jpg"),
                        ps.smartCreate("Чипси 60 г Розумний вибір пластинки зі смаком сиру к/уп", "9,70", "https://src.zakaz.atbmarket.com/origin/photos/50063.jpg"),
                        ps.smartCreate("Арахіс 70г Big bob смажений солоний в хрусткій оболонці зі смаком перцю чілі", "26,90", "https://src.zakaz.atbmarket.com/origin/photos/2707.jpg"),
                        ps.smartCreate("Насіння сoняшника&nbsp;170г Сан Санич смажене сoлoне", "31,90", "https://src.zakaz.atbmarket.com/origin/photos/1739.jpg"),
                        ps.smartCreate("Чіпси 100г Своя Лінія картопляні зі смаком сиру", "42,80", "https://src.zakaz.atbmarket.com/origin/photos/2819.jpg"),
                        ps.smartCreate("Фісташки 150&nbsp;г Своя лінія смажені сoлoні", "88,90", "https://src.zakaz.atbmarket.com/origin/photos/3126.jpg"),
                        ps.smartCreate("Хлібці 100 г VitaMax Флакси з насіння льону(журавлина і мигдаль,паприка і часник,томат і базил)", "28,80", "https://src.zakaz.atbmarket.com/origin/photos/4535.jpg"),
                        ps.smartCreate("Чіпси 110г Своя лінія картопляні зі смаком крабу", "42,80", "https://src.zakaz.atbmarket.com/origin/photos/868.jpg"),
                        ps.smartCreate("Соломка 40 г GONZO фрі зі смаком грибів", "10,60", "https://src.zakaz.atbmarket.com/origin/photos/24315.jpg"),
                        ps.smartCreate("Мікс арахісу смаженого солоного і кукурудзи смаженої Big Bob зі смаком сиру", "26,90", "https://src.zakaz.atbmarket.com/origin/photos/6948.jpg"),
                        ps.smartCreate("Чіпси 110г Своя лінія картопляні зі смаком паприки", "42,80", "https://src.zakaz.atbmarket.com/origin/photos/847.jpg"),
                        ps.smartCreate("Ядра насіння гарбуза 100г Розумний Вибір сушені", "30,90", "https://src.zakaz.atbmarket.com/origin/photos/6694.jpg"),
                        ps.smartCreate("Фісташки 70&nbsp;г Своя лінія смажені сoлoні", "46,90", "https://src.zakaz.atbmarket.com/origin/photos/1161.jpg"),
                        ps.smartCreate("Насіння сoняшнику 170г Лідер Снек Справжня лакoмка смажене не сoлoне", "49,30", "https://src.zakaz.atbmarket.com/origin/photos/5206.jpg"),
                        ps.smartCreate("Чипси 60 г Lay's картопляні зі смаком сметани і зелені м/уп", "34,90", "https://src.zakaz.atbmarket.com/origin/photos/47471.jpg"),
                        ps.smartCreate("Снеки 140 г SNACK MOOD з пелет пшенично-картопляні зі смаком раків м/уп", "38,20", "https://src.zakaz.atbmarket.com/origin/photos/33261.jpg"),
                        ps.smartCreate("Стружка кальмару 36г Своя Лінія сушена солона", "43,30", "https://src.zakaz.atbmarket.com/origin/photos/2304.jpg"),
                        ps.smartCreate("Арахіс 70 г Своя Лінія смажений солоний в хрусткій оболонці зі смаком сиру", "18,30", "https://src.zakaz.atbmarket.com/origin/photos/15158.jpg"),
                        ps.smartCreate("Слайси 30 г РЯБСHICK з філе курячого сушені зі смаком соєвого соусу м/у", "55,90", "https://src.zakaz.atbmarket.com/origin/photos/36261.jpg"),
                        ps.smartCreate("Арахіс 70 г Своя Лінія смажений солоний в хрусткій оболонці зі смаком бекону", "18,30", "https://src.zakaz.atbmarket.com/origin/photos/15157.jpg"),
                        ps.smartCreate("Чіпси 110г Своя лінія картопляні зі смаком бекону", "42,80", "https://src.zakaz.atbmarket.com/origin/photos/2497.jpg"),
                        ps.smartCreate("Чипси 125 Lays Baked запечені зі смаком йогурту з травами", "68,30", "https://src.zakaz.atbmarket.com/origin/photos/13168.jpg"),
                        ps.smartCreate("Чіпси 110г Своя лінія картопляні зі смаком сметани із зеленню", "42,80", "https://src.zakaz.atbmarket.com/origin/photos/846.jpg"),
                        ps.smartCreate("Путасу з перцем 36г Своя Лінія сушена солона", "38,40", "https://src.zakaz.atbmarket.com/origin/photos/2380.jpg"),
                        ps.smartCreate("Грінки житньо-пшеничні 100 г Flint Craft Grenki зі смаком Гострі джерки хвилясті м/уп", "29,90", "https://src.zakaz.atbmarket.com/origin/photos/39165.jpg"),
                        ps.smartCreate("Грінки житньо-пшеничні 100 г Flint Craft Grenki зі смаком Часник м/уп", "28,90", "https://src.zakaz.atbmarket.com/origin/photos/39167.jpg"),
                        ps.smartCreate("Арахіс 60 г Big bob смажений солоний зі смаком сметана та зелень м/уп", "18,90", "https://src.zakaz.atbmarket.com/origin/photos/50161.jpg"),
                        ps.smartCreate("Арахіс 120г Своя Лінія смажений солоний", "25,70", "https://src.zakaz.atbmarket.com/origin/photos/1016.jpg"),
                        ps.smartCreate("Арахіс&nbsp;90&nbsp;г&nbsp;Своя&nbsp;Лінія&nbsp;смажений&nbsp;солоний&nbsp;зі&nbsp;смаком&nbsp;сиру", "26,50", "https://src.zakaz.atbmarket.com/origin/photos/13170.jpg"), ps.smartCreate("Горішки арахісу 180г Розумний вибір смажені солоні", "34,60", "https://src.zakaz.atbmarket.com/origin/photos/1015.jpg"),
                        ps.smartCreate("Насіння соняшника 95 г Сан Санич смажене солоне", "30,30", "https://src.zakaz.atbmarket.com/origin/photos/9719.jpg"),
                        ps.smartCreate("Сухарики 150 г Flint пшеничні зі смаком Французький сир", "29,90", "https://src.zakaz.atbmarket.com/origin/photos/25789.jpg"),
                        ps.smartCreate("Арахіс 120 г Своя Лінія смажений солоний зі смаком бекону", "25,70", "https://src.zakaz.atbmarket.com/origin/photos/12859.jpg"),
                        ps.smartCreate("Арахіс 90 г Своя Лінія смажений солоний зі смаком бекону", "26,50", "https://src.zakaz.atbmarket.com/origin/photos/12858.jpg"),
                        ps.smartCreate("Чипси 100 г Своя Лінія картопляні зі смаком грибів", "42,80", "https://src.zakaz.atbmarket.com/origin/photos/14253.jpg"),
                        ps.smartCreate("Картопляна соломка 125 г Своя Лінія зі смаком кебаба", "41,80", "https://src.zakaz.atbmarket.com/origin/photos/14689.jpg"),
                        ps.smartCreate("Сухарики&nbsp;100&nbsp;г&nbsp;Розумний&nbsp;вибір&nbsp;житньо-пшеничні&nbsp;зі&nbsp;смаком холодцю з хроном", "11,80", "https://src.zakaz.atbmarket.com/origin/photos/2881.jpg"),
                        ps.smartCreate("Насіння сoняшника&nbsp;260г Сан Санич смажене сoлoне", "70,20", "https://src.zakaz.atbmarket.com/origin/photos/3071.jpg"),
                        ps.smartCreate("Насіння соняшника 300г Розумний вибір несолоне обсмажене", "46,70", "https://src.zakaz.atbmarket.com/origin/photos/1102.jpg"),
                        ps.smartCreate("Насіння сoняшника&nbsp;170г Сан Санич смажене не сoлoне", "48,30", "https://src.zakaz.atbmarket.com/origin/photos/1709.jpg"),
                        ps.smartCreate("Насіння соняшника 135г Розумний вибір несолоне обсмажене", "17,30", "https://src.zakaz.atbmarket.com/origin/photos/2742.jpg"),
                        ps.smartCreate("Сухарики пшеничні 70 г Flint Baguette зі смаком Крильця Баффало з соусом кисло-солодкий м/уп", "18,90", "https://src.zakaz.atbmarket.com/origin/photos/40585.jpg"),
                        ps.smartCreate("Насіння сoняшника&nbsp;80г Сан Санич Premium смажене не сoлoне", "30,30", "https://src.zakaz.atbmarket.com/origin/photos/13145.jpg"),
                        ps.smartCreate("Слайси 30 г РЯБСHICK з філе курячого сушені з паприкою та перцем м/уп", "62,20", "https://src.zakaz.atbmarket.com/origin/photos/36065.jpg"),
                        ps.smartCreate("Кукурудзяні палички 55 г Cheetos зі смаком сиру", "20,90", "https://src.zakaz.atbmarket.com/origin/photos/23397.jpg"),
                        ps.smartCreate("Насіння соняшника 135г Розумний вибір підсолене обсмажене", "20,30", "https://src.zakaz.atbmarket.com/origin/photos/2698.png"),
                        ps.smartCreate("Насіння 150г Своя лінія гарбузове смажене солоне", "42,80", "https://src.zakaz.atbmarket.com/origin/photos/5291.jpg"),
                        ps.smartCreate("Кальмари 30г Своя Лінія сушені солоні павутинка", "43,30", "https://src.zakaz.atbmarket.com/origin/photos/1111.jpg"),
                        ps.smartCreate("Чипси 165 г PRINGLES картопляні зі смаком техаського соусу барбекю к/уп", "107,60", "https://src.zakaz.atbmarket.com/origin/photos/45813.jpg"),
                        ps.smartCreate("Арахіс 60 г Big bob смажений солоний м/уп", "21,90", "https://src.zakaz.atbmarket.com/origin/photos/33777.jpg"),
                        ps.smartCreate("Чіпси 110г Своя лінія картопляні зі смаком сиру", "42,80", "https://src.zakaz.atbmarket.com/origin/photos/1361.jpg"),
                        ps.smartCreate("Сухарики 165 г Maretti брускети зі смаком сметани та цибулі", "45,50", "https://src.zakaz.atbmarket.com/origin/photos/48185.jpg"),
                        ps.smartCreate("Чіпси 200г Своя Лінія картопляні зі смаком сметани із зеленню", "69,40", "https://src.zakaz.atbmarket.com/origin/photos/4610.jpg"),
                        ps.smartCreate("Чіпси 200г Своя Лінія хвилясті картопляні зі смаком крилець барбекю", "69,40", "https://src.zakaz.atbmarket.com/origin/photos/4609.jpg"),
                        ps.smartCreate("Кукурудзяні кільця 55 г Crispy Cris зі смаком Сметана із зеленню м/уп", "16,70", "https://src.zakaz.atbmarket.com/origin/photos/40575.jpg"),
                        ps.smartCreate("Сухарики 125 г Flint пшенично-житні зі смаком Вершки та зелень", "13,90", "https://src.zakaz.atbmarket.com/origin/photos/37753.jpg"),
                        ps.smartCreate("Арахіс 120г Своя лінія смажений солоний зі смаком сиру", "25,70", "https://src.zakaz.atbmarket.com/origin/photos/2925.jpg"),
                        ps.smartCreate("Чіпси 60г Розумний вибір пластинки зі смаком сметани із зеленню", "9,70", "https://src.zakaz.atbmarket.com/origin/photos/2450.jpg"),
                        ps.smartCreate("Чіпси 80г Sosedi (кисле, солодке яблуко)", "74,70", "https://src.zakaz.atbmarket.com/origin/photos/46451.jpg"),
                        ps.smartCreate("Чіпси 110г Своя лінія картопляні рифлені зі смаком васабі", "42,80", "https://src.zakaz.atbmarket.com/origin/photos/1362.jpg"),
                        ps.smartCreate("Сухарики 85 г Maretti брускети зі смаком грибів із сметаною", "30,60", "https://src.zakaz.atbmarket.com/origin/photos/48139.jpg"),
                        ps.smartCreate("Чипси 133 г Люкс зі смаком сиру м/уп", "55,60", "https://src.zakaz.atbmarket.com/origin/photos/47365.jpg"),
                        ps.smartCreate("Сухарики 85 г Maretti брускети зі смаком суміші сирів", "30,60", "https://src.zakaz.atbmarket.com/origin/photos/48137.jpg"),
                        ps.smartCreate("Чипси 133 г Люкс зі смаком паприки м/уп", "48,30", "https://src.zakaz.atbmarket.com/origin/photos/47367.jpg"),
                        ps.smartCreate("Грінки 125 г Flint житні зі смаком томату Спайс", "13,90", "https://src.zakaz.atbmarket.com/origin/photos/51027.jpg"),
                        ps.smartCreate("Чипси 120 г Lay's картопляні зі смаком сиру з перцем м/уп", "48,50", "https://src.zakaz.atbmarket.com/origin/photos/50167.jpg"),
                        ps.smartCreate("Арахіс 70 г Своя Лінія смажений солоний в хрусткій оболонці зі смаком васабі", "18,30", "https://src.zakaz.atbmarket.com/origin/photos/13384.jpg"),
                        ps.smartCreate("Ядра насіння соняшника 100 г Своя лінія обсмажені", "18,80", "https://src.zakaz.atbmarket.com/origin/photos/12293.jpg"),
                        ps.smartCreate("Жовтий смугастик 36г Своя Лінія сушений солоний", "38,40", "https://src.zakaz.atbmarket.com/origin/photos/645.jpg"),
                        ps.smartCreate("Кільця 40 г GONZO кукурудзяні зі смаком кальмара", "9,20", "https://src.zakaz.atbmarket.com/origin/photos/24307.jpg"),
                        ps.smartCreate("Чипси120 г Lay's картопляні зі смаком сметани та зелені м/уп", "57,90", "https://src.zakaz.atbmarket.com/origin/photos/35447.jpg"),
                        ps.smartCreate("Чипси 120 г Lay's зі смаком бекону м/уп", "58,20", "https://src.zakaz.atbmarket.com/origin/photos/35445.jpg"),
                        ps.smartCreate("Чипси 100 г El Sabor Начо кукурудзяні зі смаком барбекю", "37,70", "https://src.zakaz.atbmarket.com/origin/photos/9452.jpg"),
                        ps.smartCreate("Чипси 120 г Lay's картопляні зі смаком лобстера м/уп", "58,20", "https://src.zakaz.atbmarket.com/origin/photos/35443.jpg"),
                        ps.smartCreate("Чипси 100 г El Sabor Начо кукурудзяні зі смаком сиру", "37,70", "https://src.zakaz.atbmarket.com/origin/photos/9451.jpg"),
                        ps.smartCreate("Павутинка 35 г Aris Пивні традиціі солено-сушена з судака", "35,90", "https://src.zakaz.atbmarket.com/origin/photos/14194.jpg"),
                        ps.smartCreate("Чипси 120 г Lay's картопляні зі смаком крабу м/уп", "57,90", "https://src.zakaz.atbmarket.com/origin/photos/35441.jpg"),
                        ps.smartCreate("Чіпси 165г PRINGLES картопляні зі смаком сиру", "139,70", "https://src.zakaz.atbmarket.com/origin/photos/2270.jpg"),
                        ps.smartCreate("Соломка 40г Високий посол з м`яса лосося сушена", "34,90", "https://src.zakaz.atbmarket.com/origin/photos/2904.jpg"),
                        ps.smartCreate("Сухaрики 125&nbsp;г Flint пшeнично-житнi зi cмaком Смажений бeкон", "20,70", "https://src.zakaz.atbmarket.com/origin/photos/37755.jpg"),
                        ps.smartCreate("Насіння сoняшнику 170 г Лідер Снек Справжня лакoмка смажене сoлoне", "49,30", "https://src.zakaz.atbmarket.com/origin/photos/45485.jpg"),
                        ps.smartCreate("Путасу 36г Своя Лінія сушена солона", "38,40", "https://src.zakaz.atbmarket.com/origin/photos/613.jpg"),
                        ps.smartCreate("Сухарики 120 г Своя лінія пшенично-житні зі смаком бекону м/уп", "17,90", "https://src.zakaz.atbmarket.com/origin/photos/27423.jpg"),
                        ps.smartCreate("Сухарики 60 г  Flint Baguette пшеничні зі смаком лобстера м/уп", "13,40", "https://src.zakaz.atbmarket.com/origin/photos/34161.jpg"),
                        ps.smartCreate("Чіпси 165г PRINGLES картопляні зі смаком сметани та цибулі", "107,60", "https://src.zakaz.atbmarket.com/origin/photos/1701.jpg"),
                        ps.smartCreate("Снеки 140 г SNACK MOOD з пелет пшенично-картопляні зі смаком курячіх крилець барбекю м/уп", "37,90", "https://src.zakaz.atbmarket.com/origin/photos/33269.jpg"),
                        ps.smartCreate("Кільця кальмару 42г Морські копчені", "40,80", "https://src.zakaz.atbmarket.com/origin/photos/4341.jpg"),
                        ps.smartCreate("Чипси 165 г PRINGLES картопляні зі смаком паприки", "139,70", "https://src.zakaz.atbmarket.com/origin/photos/47255.jpg"),
                        ps.smartCreate("Грінки 120 г Своя лінія житні зі смаком баварськi ковбаски м/уп", "17,90", "https://src.zakaz.atbmarket.com/origin/photos/27427.jpg"),
                        ps.smartCreate("Сухарики 120 г Своя лінія пшенично-житні зі смаком cметани із зеленню м/уп", "17,90", "https://src.zakaz.atbmarket.com/origin/photos/27425.jpg"),
                        ps.smartCreate("Насіння соняшника 300г Розумний вибір підсолене обсмажене", "38,70", "https://src.zakaz.atbmarket.com/origin/photos/1356.jpg")
                ));

        sellerService.saveWithProducts(
                new Seller(
                        "Петро Олексійович",
                        "petro@example.com",
                        "999-999-9999",
                        "password",
                        "вул. Продуктова, 34",
                        "KFC",
                        imageService.smartSave("https://res.cloudinary.com/glovoapp/q_30,f_auto,c_fill,h_250,w_450/v1688478235/promoimport/dpnddqcbyeivmcwk0yrj.jpg"),
                        SellerType.Restaurant,
                        cityService.smartSave("Житомир")
                )
                ,
                Arrays.asList(
                        ps.smartCreate("Хот Бакет 26", "462,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/wah5t9voasuvoeu1htzb"),
                        ps.smartCreate("Хот Бакет 18", "358,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/fru1cjudrswen2dm0gb4"),
                        ps.smartCreate("Дует Бакет ориг.", "297,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/nfk0ngtdoqrmvkyybcug"),
                        ps.smartCreate("Хот Бакет 12", "275,00", "https://kfc-images.s3.eu-west-1.amazonaws.com/img/S_Bucket_14_HW_out.png"),
                        ps.smartCreate("Кентуккi бургер меню", "209,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/k1jftr3m24az0i9jv5q6"),
                        ps.smartCreate("Шефбургер меню", "198,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/yembsrjwhcnhtbmezsfm"),
                        ps.smartCreate("Сандерс Бакет OR", "176,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/fnxzwfjw4c5fqh0gr7xf"),
                        ps.smartCreate("Кентуккi бургер", "121,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/cei2uvqzodgxxbtnmpc6"),
                        ps.smartCreate("Байтс Теріякі", "121,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/z0gmodegozat5pajqh4s"),
                        ps.smartCreate("Нагетси 9 шт.", "121,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/xy5iro5tgjbgkxakv9dh")
                ));

        sellerService.saveWithProducts(
                new Seller(
                        "Жуан Італьчік",
                        "italio.one.love@example.com",
                        "999-777-7777",
                        "password",
                        "вул. Італійська, 44",
                        "Maco BBQ",
                        imageService.smartSave("https://res.cloudinary.com/glovoapp/q_30,f_auto,c_fill,h_250,w_450/Stores/iizm8mwaxozelrugqq6l"),
                        SellerType.Restaurant,
                        cityService.smartSave("Житомир")
                )
                ,
                Arrays.asList(
                        ps.smartCreate("Піца Ocean Paradise 25см/450 г", "195,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/aj8pngn6wxqebc9sz57o"),
                        ps.smartCreate("Піца Італьяно 25 см/400 г", "119,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/bnqntctfso2mheidtkm9"),
                        ps.smartCreate("Піца Мажор (25см/370г)", "109,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/n1pvdrivikxijofnne3b"),
                        ps.smartCreate("Піца 4 М'яса (25см/405)", "109,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/zi4ruda1bpclooyd1rvu"),
                        ps.smartCreate("Піца Хіросіма для любителів гострих відчуттів (25 см/380 г))", "90,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/usjmmn9haoww7bqqiqh2"),
                        ps.smartCreate("Піца 4 сири (25см/370г)", "90,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/ckjl93ohkmexxvodnequ"),
                        ps.smartCreate("Піца BBQ (25см/400г)", "90,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/wzhshjwqdjdjcppgltsg"),
                        ps.smartCreate("Піца Жульєн (25см/410г)", "89,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/dosrlgokyhtf4ti8ibrk"),
                        ps.smartCreate("Піца Пікантна (25 см/400г)", "85,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/lgdwleycckcx0kyuj1cb"),
                        ps.smartCreate("Піца Салямі (25см/385г)", "85,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/ppjcasuqpu2ojyjluzgf"),
                        ps.smartCreate("Піца Гавайська (25 см/400г)", "85,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/cohd7d0rwawswxhyu1ty"),
                        ps.smartCreate("Піца Карбонара 25 см/305 г", "77,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/zb3shbfp7sjocacud4if"),
                        ps.smartCreate("Піца Маргарита (25см/350г)", "75,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/jrrhh36q1xsrqxx1uxuc"),
                        ps.smartCreate("Піца Ocean Paradise 30см/640 гр", "280,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/rbenygetwxsgiogl8b2t"),
                        ps.smartCreate("Піца Італьяно 30 см/570 гр", "179,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/ugpsqvlusjzijh2lfnwu"),
                        ps.smartCreate("Піца Мажор (30см/610г)", "165,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/strf2dcahblcsx2f0ljl"),
                        ps.smartCreate("Піца 4 М'яса (30см/590г)", "165,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/sbi8ui8fiibptzr3af99"),
                        ps.smartCreate("Піца Жульен (30 см/575г)", "165,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/nmsekw56bw5dhz0wskxe"),
                        ps.smartCreate("Піца одна, а смаки різні (30см)", "165,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/vusfjiwjsi7tkpwr6d1f"),
                        ps.smartCreate("Піца 4 сири (30см/530г)", "159,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/c6kjixkvkziyalalloua"),
                        ps.smartCreate("Піца Хіросіма для любителів гострих відчуттів 30 см/560г", "155,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/ak6anddhyhjcwel10yqj"),
                        ps.smartCreate("Піца Цезарь (30 см/590г)", "155,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/vrolj7klgsqjtuvmuiuj"),
                        ps.smartCreate("Піца BBQ (30см/560г)", "155,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/xcsiay39mqubqs9gbhr1"),
                        ps.smartCreate("Піца Гавайська (30см/550г)", "155,00", "https://res.cloudinary.com/glovoapp/f_auto,c_thumb,h_96,w_96,q_auto:low/Products/bqchctnalxyoct1qfh5f")
                ));

        return "redirect:/moderator";
    }
}
