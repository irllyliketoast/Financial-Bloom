package csc450.BackEnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean isAlpha(String input) {
        return input != null && input.chars().allMatch(Character::isLetter);
    }

    public String updateUserInfo(int userId, UserUpdateRequest updateRequest) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return "User not found.";
        }

        if (!isAlpha(updateRequest.getNewFName())) {
            return "Invalid first name. Only letters allowed.";
        }
        if (!isAlpha(updateRequest.getNewLName())) {
            return "Invalid last name. Only letters allowed.";
        }

        user.setNewFName(updateRequest.getNewFName());
        user.setNewLName(updateRequest.getNewLName());
        user.setNewEmail(updateRequest.getNewEmail());

        userRepository.save(user);
        return "User info successfully updated.";
    }
}
