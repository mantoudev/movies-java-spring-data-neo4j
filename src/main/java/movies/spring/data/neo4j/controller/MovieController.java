package movies.spring.data.neo4j.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import movies.spring.data.neo4j.domain.Movie;
import movies.spring.data.neo4j.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mark Angrish
 * @author Michael J. Simons
 */
@RestController
@RequestMapping("/movie")
public class MovieController {

	private final MovieService movieService;
	
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

    @GetMapping("/graph")
	public Map<String, Object> graph(@RequestParam(value = "limit",required = false) Integer limit) {
		return movieService.graph(limit == null ? 100 : limit);
	}

	/***************** Add By Mantoudev ****************/

	@GetMapping("/{id}")
	public Movie findById(@PathVariable("id") Long id) {
		return movieService.findById(id);
	}

	@GetMapping("/search")
	public Movie findByTitle(@RequestParam("title") String title) {
		return movieService.findByTitle(title);
	}

	@GetMapping("/all")
	public List<Movie> findMovies(@RequestParam("page") int page, @RequestParam("size") int size) {
		return movieService.findAll(page, size);
	}

	@PostMapping("/")
	public Movie createMovie(@RequestBody Movie movie) {
		return movieService.createMovie(movie);
	}

	@PutMapping("/")
	public void updateMovie(@RequestBody Movie movie) {
		movieService.updateMovie(movie);
	}

	@DeleteMapping("/{id}")
	public void deleteMovie(@PathVariable("id") Long id) {
		movieService.delete(id);
	}

}
