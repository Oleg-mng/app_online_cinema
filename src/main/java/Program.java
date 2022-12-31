/**
 * Информационная система работы с фильмотекой
 * Требуется спроектировать приложение по работе с данными,
 * в которой есть несколько таблиц с данными по работе с фильмами
 *
 * 1. Имя
 * 2. Киностудия
 * 3. Год
 * 4. Жанр
 * 5. Длительность
 *
 * 1. Спроектировать всю структуру
 * 2. Написать код
 *
 */

import java.util.ArrayList;

    public class Program {
        public static void main(String[] args) {
            FilmFinder finder = new FilmFinder();

            System.out.println(finder.getAllInfoBy(1));
            System.out.println(finder.getAllInfoBy(2));
            System.out.println(finder.getAllInfoBy(3));
            System.out.println(finder.getAllInfoBy(4));

        }
    }

    class FilmFinder {
        public FilmFinder() {
            init();
        }

        DataBase db;

        public DataBase getDb() {
            return db;
        }

        public String getAllInfoBy(int id) {
            Cinema c = db.films.get(id - 1);

            return String.format("%d %s %s %s",
                    c.id,
                    c.name,
                    db.genres.get(c.genreId - 1).name,
                    db.studios.get(c.creatorId - 1).titleName);

        }

        DataBase init() {
            db = new DataBase();
            Cinema c1 = new Cinema(1, "Мажор", 1, 1,1,1);
            Cinema c2 = new Cinema(2, "Гринч", 2, 2,2,2);
            Cinema c3 = new Cinema(3, "Викинги", 3, 4,3,3);
            Cinema c4 = new Cinema(4, "Интерстеллер", 4, 3,4,4);

            db.films.add(c1);
            db.films.add(c2);
            db.films.add(c3);
            db.films.add(c4);

            db.genres.add(new Genre(1, "боевик"));
            db.genres.add(new Genre(2, "мультфильм"));
            db.genres.add(new Genre(3, "исторический"));
            db.genres.add(new Genre(4, "фантастика"));

            FilmCreatorFactory fp = new FilmCreatorFactory();

            db.addStudios(fp.getFilmProducer("Иван Самохвалов"));
            db.addStudios(fp.getFilmProducer("Одри Гейсел"));
            db.addStudios(fp.getFilmProducer("Майкл Хёрст"));
            db.addStudios(fp.getFilmProducer("Кристофер Нолан"));

            return db;
        }
    }

    class DataBase {
        ArrayList<Cinema> films = new ArrayList<>();
        ArrayList<Studio> studios = new ArrayList<>();
        ArrayList<Genre> genres = new ArrayList<>();

        public void addStudios(Studio producer) {
            studios.add(producer);
        }
    }

    class Cinema {
        int id;
        int creatorId;
        String name;
        int genreId;
        int actorId;
        int countryId;

        public Cinema(int cinemaId, String name, int genreId, int creatorId, int actorId, int countryId) {
            this.id = cinemaId;
            this.creatorId = creatorId;
            this.name = name;
            this.genreId = genreId;
            this.actorId = actorId;
            this.countryId = countryId;
        }
    }

    class Studio {
        int id;
        String titleName;
        String factName;
    }

    class Genre {
        int id;
        String name;

        public Genre(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    class FilmCreatorFactory {
        int count = 1;

        public Studio getFilmProducer(String name) {
            Studio fp = new Studio();
            fp.id = count++;
            fp.titleName = name;
            return fp;
        }
    }

    class Country {
        int id;
        String name;
    }

    class Actor {
        int id;
        String name;
        int year;
        int totalFilms;
        int growth;
    }

