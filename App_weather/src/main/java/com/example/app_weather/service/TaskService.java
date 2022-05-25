package com.example.app_weather.service;

import com.example.app_weather.dto.ApiResponse;
import com.example.app_weather.dto.TaskDto;
import com.example.app_weather.entity.Role;
import com.example.app_weather.entity.Task;
import com.example.app_weather.entity.User;
import com.example.app_weather.entity.enums.TaskStatus;
import com.example.app_weather.repository.TaskRepository;
import com.example.app_weather.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TaskService {
    final UserRepository userRepository;
    final TaskRepository taskRepository;

    public ApiResponse add(TaskDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Set<Role> fromUserRoles = user.getRoleList();

        Optional<User> optionalUser = userRepository.findById(dto.getUserId());
        Set<Role> toUserRoles = optionalUser.get().getRoleList();

        if (fromUserRoles.size() > toUserRoles.size()) {
            Task task = new Task();
            task.setDescription(dto.getDescription());
            task.setGivenUser(user);
            task.setTakenUser(optionalUser.get());
            task.setDue(dto.getDueDate());
            task.setStatus(TaskStatus.NEW);
            task.setName(dto.getName());
            taskRepository.save(task);
        }
        return ApiResponse.builder().success(true).message("Vazifa biriktirildi!").build();
    }

    public ApiResponse changeStatus(Integer id, String status) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        Task task = optionalTask.get();
        TaskStatus taskStatus = TaskStatus.valueOf(status);

        if (task.getStatus().ordinal() < taskStatus.ordinal()) {
            if (taskStatus.ordinal() == 3 && task.getDue().before(new Timestamp(new Date().getTime()))) {
                task.setCompletedTime(new Timestamp(new Date().getTime()));
                task.setCompleted(true);
                task.setStatus(TaskStatus.COMPLETED);
            } else {
                task.setStatus(taskStatus);
            }
        } else {
            return ApiResponse.builder().message("Statusni bu holatga qaytarish mnmas!").success(false).build();
        }
        taskRepository.save(task);
        return ApiResponse.builder().message("Changed").success(true).build();
    }
}
