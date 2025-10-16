package documentanalysis.filesize.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import documentanalysis.filesize.entity.FileData;

public interface FileDataRepository extends JpaRepository<FileData, Long> {
}
