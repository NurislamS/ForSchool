package kfu.project.repository;

import kfu.project.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nurislam on 19.07.2018.
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
}
