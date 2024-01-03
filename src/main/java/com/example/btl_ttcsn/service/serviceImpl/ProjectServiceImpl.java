package com.example.btl_ttcsn.service.serviceImpl;

import com.example.btl_ttcsn.dto.common.UserDetailDTO;
import com.example.btl_ttcsn.dto.request.project.ProjectCreateRequestDTO;
import com.example.btl_ttcsn.dto.response.project.ProjectCreateResponseDTO;
import com.example.btl_ttcsn.dto.response.project.ProjectDetailResponseDTO;
import com.example.btl_ttcsn.dto.response.project.ProjectParentsResponseDTO;
import com.example.btl_ttcsn.entity.Location;
import com.example.btl_ttcsn.entity.Project;
import com.example.btl_ttcsn.entity.User;
import com.example.btl_ttcsn.exception.NotFoundException;
import com.example.btl_ttcsn.repository.ProjectRepository;
import com.example.btl_ttcsn.repository.UserRepository;
import com.example.btl_ttcsn.service.ProjectSerivce;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectSerivce {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Override
    public ProjectCreateResponseDTO create(ProjectCreateRequestDTO projectCreateRequestDTO) {
        Project project=modelMapper.map(projectCreateRequestDTO,Project.class);
        projectRepository.save(project);
        return modelMapper.map(project, ProjectCreateResponseDTO.class);
    }

    @Override
    public ProjectParentsResponseDTO addProject(Long idParents,Long idChild) {
        Project projectParents=projectRepository.findById(idParents).orElse(null);
        Project project=projectRepository.findById(idChild).orElse(null);
        if(projectParents.getId()==project.getId()){
            throw new NotFoundException("duplicate ID!");
        }
        if(projectParents==null||project==null){
            throw new NotFoundException("Not Found ID Project!");
        }
        project.setProjectParent(projectParents);
        projectRepository.save(project);
        ProjectParentsResponseDTO projectParentsResponseDTO=modelMapper.map(projectParents,ProjectParentsResponseDTO.class);
        List<ProjectDetailResponseDTO> projects=new ArrayList<>();
        for(Project x:projectParents.getProjects()){
            projects.add(modelMapper.map(x,ProjectDetailResponseDTO.class));
        }
        projectParentsResponseDTO.setProjects(projects);
        Location location=project.getLocation();
        if(location!=null){
            projectParentsResponseDTO.setLocation(project.getLocation());
        }
        return projectParentsResponseDTO;
    }

    @Override
    public ProjectCreateResponseDTO update(ProjectCreateResponseDTO projectCreateResponseDTO) {
        Project project=modelMapper.map(projectCreateResponseDTO,Project.class);
        Project pj=projectRepository.findById(project.getId()).orElseThrow(()->new NotFoundException("Not Found Project"));
            pj.setName(project.getName());
            pj.setMaterial(project.getMaterial());
            pj.setBudget(project.getBudget());
            pj.setDescription(project.getDescription());
            pj.setStatus(project.getStatus());
            pj.setStartday(project.getStartday());
            pj.setDeadline(project.getDeadline());
            projectRepository.save(pj);
            return modelMapper.map(pj,ProjectCreateResponseDTO.class);

    }

    @Override
    public void remove(Long id) {
        Project project=projectRepository.findById(id).orElseThrow(()->new NotFoundException("Not Found ID Project!"));
        projectRepository.deleteById(id);
    }

    @Override
    public List<ProjectCreateResponseDTO> findAll() {
        List<Project> projects=projectRepository.findAll();
        List<ProjectCreateResponseDTO> list=new ArrayList<>();
        for(Project x:projects){
            if(x.getProjectParent()==null){
                list.add(modelMapper.map(x,ProjectCreateResponseDTO.class));
            }
        }
        return list;
    }

    @Override
    public ProjectParentsResponseDTO findById(Long id) {
        Project project=projectRepository.findById(id).orElseThrow(()->new NotFoundException("Not Found ID Project!"));
        ProjectParentsResponseDTO projectParentsResponseDTO=modelMapper.map(project,ProjectParentsResponseDTO.class);
        Location location=project.getLocation();
        if(location!=null){
            projectParentsResponseDTO.setLocation(project.getLocation());
        }

        List<UserDetailDTO> userDetailDTOS =new ArrayList<>();
        for(User x:project.getUsers()){
            userDetailDTOS.add(modelMapper.map(x,UserDetailDTO.class));
        }
        projectParentsResponseDTO.setPeople(userDetailDTOS);
        List<ProjectDetailResponseDTO> projects=new ArrayList<>();
        for(Project x:project.getProjects()){
            projects.add(modelMapper.map(x,ProjectDetailResponseDTO.class));
        }
        projectParentsResponseDTO.setProjects(projects);
        return  projectParentsResponseDTO;
    }

    @Override
    public ProjectDetailResponseDTO addUser(Long idProject, Long userId) {
        Project project=projectRepository.findById(idProject).orElse(null);
        if(project==null){
            throw  new NotFoundException("ID Project Is Not Found!");
        }
        User user=userRepository.findById(userId).orElse(null);
        if(user==null){
           throw  new NotFoundException("ID User Is Not Found!");
        }
        user.setProject(project);
        userRepository.save(user);
        ProjectDetailResponseDTO projectDetailResponseDTO=modelMapper.map(project,ProjectDetailResponseDTO.class);
        List<UserDetailDTO> userDetailDTOS =new ArrayList<>();
        for(User x:project.getUsers()){
            userDetailDTOS.add(modelMapper.map(x,UserDetailDTO.class));
        }
        projectDetailResponseDTO.setUsers(userDetailDTOS);
        return projectDetailResponseDTO;
    }


}
