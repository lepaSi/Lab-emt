package mk.ukim.finki.emtaud.repository.views;

import mk.ukim.finki.emtaud.model.views.AuthorPerCountryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorsPerCountryViewRepository extends JpaRepository<AuthorPerCountryView,Long> {

}
