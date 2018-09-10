package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.models.Project;
import co.edu.unal.vsacode.moodleplz.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository repository;

    public ProjectService(){
    }

    /**
     * Provides a list with all the project on
     * the repository
     * @return a list project
     */
    public List<Project> getProject(){
        return repository.findAll();
    }

    /**
     * Search and show the project with the id provided
     * @param id generated id
     * @return Optional project
     */
    public Optional<Project> getProject(String id) {
        return repository.findById(id);
    }

    /**
     * Search and show the projects with the code project provided
     * @param name code of the project provided for the user
     * @return a list with all the projects which the code matches with the code provided
     */
    public Optional<Project> getProjectbyName(String name){
        return repository.findByName(name);
    }

    /**
     * Save the provided project on the project repository
     * @param project company´s project
     * @return the project that was saved
     */
    public Project saveProject(Project project){
        return repository.save(project);
    }

    /**
     * Delete of the project repository the provided project
     * @param id company´s project id
     */
    public void deleteProject(String id){
        repository.deleteById(id);
    }
}
