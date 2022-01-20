package es.davidabellannavarro.springbootjdbc;

import java.util.List;
import java.util.Optional;

/**
 * @author David Abellán Navarro
 * @project SpringBootJDBC
 * @course 2ºD.A.M.
 * @date 19/01/2022
 */

public interface PersonajeRepository {

    int count();

    int save(Personaje  personaje);

    int update(Personaje personaje);

    int deleteById(Long id);

    List<Personaje> findAll();

    List<Personaje> findByNombreAndClase(String nombre, String clase);

    Optional<Personaje> findByNombre(String nombre);

    String getNombreByClase(String clase);

    Optional<Personaje> findById(Long id);
}
