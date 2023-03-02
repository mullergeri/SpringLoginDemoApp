package com.example.springlogindemo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    @Transactional
    @Modifying
    @Query("update ToDo t set t.done = ?1 where t.id = ?2 and t.owner = ?3")
    void updateDoneByIdAndOwner(Boolean done, Long id, AppUser owner);

    @Query("select t from ToDo t where t.owner = ?1")
    List<ToDo> findByOwner(AppUser owner);

    @Transactional
    @Modifying
    @Query("delete from ToDo t where t.id = ?1 and t.owner = ?2")
    void deleteByIdAndOwner(Long id, AppUser owner);



}
