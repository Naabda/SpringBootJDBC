package es.davidabellannavarro.springbootjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author David Abellán Navarro
 * @project SpringBootJDBC
 * @course 2ºD.A.M.
 * @date 19/01/2022
 */

@Repository
public class JdbcPersonajeRepository implements PersonajeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate
            .queryForObject("select count(*) from personajes", Integer.class);
    }

    @Override
    public int save(Personaje personaje) {
        return jdbcTemplate.update(
            "insert into personajes (nombre, clase, vida_max, energia_max, vida_actual, energia_actual, monedas) values(?,?,?,?,?,?,?)",
            personaje.getNombre(), personaje.getClase(), personaje.getVida_max(), personaje.getEnergia_max(), personaje.getVida_actual(),
            personaje.getEnergia_actual(), personaje.getMonedas());
    }

    @Override
    public int update(Personaje personaje) {
        return jdbcTemplate.update(
            "update personajes set nombre = ?, clase = ?, vida_max = ?, energia_max = ?, vida_actual = ?, energia_actual = ?, monedas = ?  where id = ?",
            personaje.getNombre(), personaje.getClase(), personaje.getVida_max(), personaje.getEnergia_max(), personaje.getVida_actual(),
            personaje.getEnergia_actual(), personaje.getMonedas(), personaje.getId());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update(
            "delete personajes where id = ?",
            id);
    }

    @Override
    public List<Personaje> findAll() {
        return jdbcTemplate.query(
            "select * from personajes",
            (rs, rowNum) ->
                new Personaje(
                    rs.getLong("id"),
                    rs.getString("nombre"),
                    rs.getString("clase"),
                    rs.getInt("vida_max"),
                    rs.getInt("energia_max"),
                    rs.getInt("vida_actual"),
                    rs.getInt("energia_max"),
                    rs.getInt("monedas")
                )
        );
    }

    @Override
    public List<Personaje> findByNombreAndClase(String nombre, String clase) {
        return jdbcTemplate.query(
            "select * from personajes where nombre like ? and clase <= ?",
            new Object[]{"%" + nombre + "%", clase},
            (rs, rowNum) ->
                new Personaje(
                    rs.getLong("id"),
                    rs.getString("nombre"),
                    rs.getString("clase"),
                    rs.getInt("vida_max"),
                    rs.getInt("energia_max"),
                    rs.getInt("vida_actual"),
                    rs.getInt("energia_max"),
                    rs.getInt("monedas")
                )
        );
    }

    @Override
    public Optional<Personaje> findByNombre(String nombre) {
        return jdbcTemplate.queryForObject(
            "select * from personajes where nombre = ?",
            new Object[]{nombre},
            (rs, rowNum) ->
                Optional.of(new Personaje(
                    rs.getLong("id"),
                    rs.getString("nombre"),
                    rs.getString("clase"),
                    rs.getInt("vida_max"),
                    rs.getInt("energia_max"),
                    rs.getInt("vida_actual"),
                    rs.getInt("energia_max"),
                    rs.getInt("monedas")
                ))
        );
    }

    @Override
    public String getNombreByClase(String nombre) {
        return jdbcTemplate.queryForObject(
                "select clase from personajes where nombre = ?",
                new Object[]{nombre},
                String.class
        );
    }

    @Override
    public Optional<Personaje> findById(Long id) {
        return jdbcTemplate.queryForObject(
            "select * from personajes where id = ?",
            new Object[]{id},
            (rs, rowNum) ->
                Optional.of(new Personaje(
                    rs.getLong("id"),
                    rs.getString("nombre"),
                    rs.getString("clase"),
                    rs.getInt("vida_max"),
                    rs.getInt("energia_max"),
                    rs.getInt("vida_actual"),
                    rs.getInt("energia_max"),
                    rs.getInt("monedas")
                ))
        );
    }
}
