package es.davidabellannavarro.springbootjdbc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringBootJdbcApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(SpringBootJdbcApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJdbcApplication.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    JdbcPersonajeRepository personajeRepository;

    /**
     * Esto es el tutorial comentado porque quise investigar un poco más y hacer mis propias SQL. De ahí que volviera
     * hacer el código debajo.
     */
    //    @Override
//    public void run(String... strings) throws Exception {
//
//        log.info("Creating tables");
//
//        jdbcTemplate.execute("DROP TABLE personajes IF EXISTS");
//        jdbcTemplate.execute("CREATE TABLE personajes(" +
//                "id SERIAL, nombre VARCHAR(255), clase VARCHAR(255))");
//
//        // Split up the array of whole names into an array of name/class
//        List<Object[]> splitUpNames = Arrays.asList("Paco mago", "Dean guerrero", "Javier orco", "Pepe mago").stream()
//                .map(name -> name.split(" "))
//                .collect(Collectors.toList());
//
//        // Use a Java 8 stream to print out each tuple of the list
//        splitUpNames.forEach(name -> log.info(String.format("Escribe sin espacios nombre y después la clase  %s %s", name[0], name[1])));
//
//        // Uses JdbcTemplate's batchUpdate operation to bulk load data
//        jdbcTemplate.batchUpdate("INSERT INTO personajes(nombre, clase) VALUES (?,?)", splitUpNames);
//
//        log.info("Querying for personajes records where nombre = 'David':");
//        jdbcTemplate.query(
//                "SELECT  * FROM personajes"
//                /*"SELECT id, nombre, clase FROM personajes WHERE nombre = ?", new Object[] { "David" }*/,
//                (rs, rowNum) -> new Personaje(rs.getLong("id"), rs.getString("nombre"), rs.getString("clase"))
//        ).forEach(customer -> log.info(customer.toString()));
//
//        jdbcTemplate.execute("INSERT INTO personajes (nombre, clase) VALUES (?, ?)", {"PepCO", "Manolo"});
//
//    }
    @Override
    public void run(String... args) {

        log.info("StartApplication...");

        startPersonajeApp();
    }

    void startPersonajeApp() {

        /**
         * La primera vez que se ejecuta el código hay que descomentar la linea 79.
         * Para las siguientes veces o se dejan las 78 y 79 comentadas para ver como crece la tabla o se deben descomentar ambas.
         */
//        jdbcTemplate.execute("DROP TABLE personajes IF EXISTS");
//        jdbcTemplate.execute("CREATE TABLE personajes(" +
//                "id SERIAL, nombre VARCHAR(255), clase VARCHAR(255), vida_max NUMERIC(3), energia_max NUMERIC(3), vida_actual NUMERIC(3), energia_actual NUMERIC(3), monedas NUMERIC(10))");

        List<Personaje> list = Arrays.asList(
                new Personaje("David", "programador", 100, 100, 40, 60, 7),
                new Personaje("Javier", "profesor", 100, 100, 85, 100, 100),
                new Personaje("JuanCarlos", "programador", 100, 100, 70, 50, 8)
        );

        list.forEach(x -> {
            log.info("Saving...{}", x.getNombre());
            personajeRepository.save(x);
        });

        log.info("[FIND_BY]");
        log.info("[BY ID]--- {}", personajeRepository.findById(1L));
        log.info("[BY NOMBRE 'Javier' Y CLASE 'profesor']--- {}", personajeRepository.findByNombreAndClase("Javier", "profesor"));

        log.info("[FIND_ALL]");
        log.info("{}", personajeRepository.findAll());

        log.info("[UPDATE_AND_FIND]");
        log.info("[FIND_BY NOMBRE 'JuanCarlos' Y CLASE 'programador']");
        List<Personaje> personajes = personajeRepository.findByNombreAndClase("JuanCarlos", "programador");
        Personaje p = personajes.get(0);
        log.info("[Encontrado]--- {}", p.toString());
        p.setMonedas(100);
        personajeRepository.update(p);
        String nombre = p.getNombre();
        String clase = p.getClase();
        log.info("[Personaje actualizado con 100 monedas]");
        log.info("{}", personajeRepository.findByNombreAndClase(nombre, clase));

        log.info("[COUNT]");
        log.info("[Hay un total de:]--- {}", personajeRepository.count());

    }
}