package com.session.library.libraryofbooks.repository;

import com.session.library.libraryofbooks.model.Role;
import com.session.library.libraryofbooks.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findOneByName(String name);
}

