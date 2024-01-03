package com.example.btl_ttcsn.service.serviceImpl;

import com.example.btl_ttcsn.Email.EmailService;
import com.example.btl_ttcsn.dto.common.UserDetailDTO;
import com.example.btl_ttcsn.dto.request.user.UserCreateRequestDTO;
import com.example.btl_ttcsn.dto.request.user.UserUpdateRequestDTO;
import com.example.btl_ttcsn.dto.response.user.UserCreateResponseDTO;
import com.example.btl_ttcsn.dto.response.user.UserUpdateResponseDTO;
import com.example.btl_ttcsn.entity.Role;
import com.example.btl_ttcsn.entity.User;
import com.example.btl_ttcsn.exception.NotFoundException;
import com.example.btl_ttcsn.exception.UnauthorizedException;
import com.example.btl_ttcsn.repository.RoleRepository;
import com.example.btl_ttcsn.repository.UserRepository;
import com.example.btl_ttcsn.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private EmailService emailService;
    @Override
    public UserCreateResponseDTO create(UserCreateRequestDTO userCreateRequestDTO) {
        User user=modelMapper.map(userCreateRequestDTO,User.class);
        if(userRepository.findByUsername(user.getUsername())!=null){
            throw new UnauthorizedException("username already exists");
        }
        if(userRepository.findByEmail(user.getEmail())!=null){
            throw new UnauthorizedException("email already exists");
        }
        Role role=roleRepository.findById(1L).orElse(new Role(1L,"user"));
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return modelMapper.map(user,UserCreateResponseDTO.class);
    }

    @Override
    public UserUpdateResponseDTO update(UserUpdateRequestDTO userUpdateRequestDTO) {
        User user=modelMapper.map(userUpdateRequestDTO,User.class);
        User u=userRepository.getById(getCurruntUser().getId());
            u.setName(user.getName());
            u.setAge(user.getAge());
            u.setGender(user.getGender());
            u.setAddress(user.getAddress());
            u.setEmail(user.getEmail());
            u.setPhone(user.getPhone());
            userRepository.save(u);
            return modelMapper.map(u,UserUpdateResponseDTO.class);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public String forgotPassword(String email) {
        try{

            String newPassword=generateRandomPassword();
            String url="http://localhost:8080/user/confirm?email="+email+"&password="+newPassword;
            String text="Chúng tôi nhận được yêu cầu thiết lập lại mật khẩu cho tài khoản của bạn. Dưới đây là mật khẩu mới của bạn:\n" +
                    "\n" +
                    "Mật khẩu mới: "+newPassword+"\n" +
                    "\n" +
                    "Vui lòng đảm bảo rằng bạn giữ mật khẩu này cẩn thận và không chia sẻ nó với người khác. Nếu bạn không thực hiện yêu cầu này, vui lòng liên hệ với chúng tôi ngay lập tức.\n" +
                    "\n" +
                    "Nếu là bạn gửi vui lòng click vào đường link sau: "+url+"\n"+
                    "Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi.\n" +
                    "\n" +
                    "Trân trọng,\n"
                    +"TTCSN";
            return emailService.sendPassword(email,text);
        }
        catch (Exception e){
            throw new NotFoundException("Invalid Email");
        }

    }

    @Override
    public UserCreateResponseDTO getCurruntUser() {
        Authentication authentication = null;
        try {
            authentication = SecurityContextHolder.getContext().getAuthentication();
            String username= (String) authentication.getPrincipal();
            User user=userRepository.findByUsername(username);
            return modelMapper.map(user,UserCreateResponseDTO.class);

        }  catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException("Login First!");
        }

    }

    @Override
    public UserDetailDTO getUserById(Long id) {
        User user=userRepository.findById(id).orElse(null);
        if(user==null){
            throw new NotFoundException("Not Found User!");
        }
        UserDetailDTO userDetailDTO=modelMapper.map(user,UserDetailDTO.class);
        userDetailDTO.setRole(user.getRole().getName());
        return userDetailDTO;
    }
    @Override
    public String updatePassword(String password) {
        Authentication authentication = null;
        try {
            authentication = SecurityContextHolder.getContext().getAuthentication();
            String username= (String) authentication.getPrincipal();
            User user=userRepository.findByUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);

            return "changed password successfully";
        }  catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException("Login First!");
        }

    }

    @Override
    public void Confirm(String email,String password) {
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new NotFoundException("Not Found Email");
        }
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public List<UserDetailDTO> getAll() {
        List<User> users=userRepository.findAll();
        List<UserDetailDTO> userDetailDTOS=new ArrayList<>();
        for(User user :users){
            userDetailDTOS.add(modelMapper.map(user,UserDetailDTO.class));
        }
        return userDetailDTOS;
    }

    public static String generateRandomPassword() {
        // Độ dài của mật khẩu
        int passwordLength = 6;

        // Ký tự được phép trong mật khẩu (ở đây là các số từ 0 đến 9)
        String allowedChars = "0123456789";

        // Sử dụng StringBuilder để hiệu quả khi làm việc với chuỗi
        StringBuilder password = new StringBuilder();

        // Tạo đối tượng Random để tạo số ngẫu nhiên
        Random random = new Random();

        for (int i = 0; i < passwordLength; i++) {
            // Chọn một ký tự ngẫu nhiên từ chuỗi allowedChars
            int randomIndex = random.nextInt(allowedChars.length());
            char randomChar = allowedChars.charAt(randomIndex);

            // Thêm ký tự ngẫu nhiên vào chuỗi mật khẩu
            password.append(randomChar);
        }
        return password.toString();
    }

}
