package ir.sadeqcloud.BontechEvaluationProject.repository.userRepository;

import ir.sadeqcloud.BontechEvaluationProject.model.userModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
}
