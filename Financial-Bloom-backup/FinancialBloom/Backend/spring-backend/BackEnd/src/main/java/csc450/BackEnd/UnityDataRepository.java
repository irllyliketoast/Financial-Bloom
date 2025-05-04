package csc450.BackEnd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnityDataRepository extends JpaRepository<UnityDataRequest, Long> {
    // Optional: Add custom queries here
}