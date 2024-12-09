package med.voll.api_alura.repositories;

import med.voll.api_alura.models.doctors.DoctorModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<DoctorModel, Long> {
    Page<DoctorModel> findAllByAtivoTrue(Pageable pageable);
}
