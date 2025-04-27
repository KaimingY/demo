package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 查询所有用户
     */
    @ApiOperation("查询所有用户")
    @GetMapping
    public Result<List<User>> list() {
        List<User> list = userService.list();
        return Result.success(list);
    }

    /**
     * 分页查询用户
     */
    @ApiOperation("分页查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页码", paramType = "query", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "size", value = "每页数量", paramType = "query", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "name", value = "用户名", paramType = "query", dataTypeClass = String.class)
    })
    @GetMapping("/page")
    public Result<Page<User>> page(@RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String name) {
        Page<User> page = new Page<>(current, size);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        // 如果名称不为空，添加名称查询条件
        if (name != null && !name.isEmpty()) {
            queryWrapper.like(User::getName, name);
        }

        Page<User> userPage = userService.page(page, queryWrapper);
        return Result.success(userPage);
    }

    /**
     * 根据ID查询用户
     */
    @ApiOperation("根据ID查询用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataTypeClass = Long.class)
    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user != null) {
            return Result.success(user);
        } else {
            return Result.error("用户不存在", 404);
        }
    }

    /**
     * 新增用户
     */
    @ApiOperation("新增用户")
    @PostMapping
    public Result<Boolean> save(@Validated @RequestBody User user) {
        boolean success = userService.save(user);
        if (success) {
            return Result.success(true, "用户创建成功");
        } else {
            return Result.error("用户创建失败");
        }
    }

    /**
     * 修改用户
     */
    @ApiOperation("修改用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataTypeClass = Long.class)
    @PutMapping("/{id}")
    public Result<Boolean> update(@PathVariable Long id, @Validated @RequestBody User user) {
        user.setId(id);
        boolean exists = userService.getById(id) != null;
        if (!exists) {
            return Result.error("用户不存在", 404);
        }

        boolean success = userService.updateById(user);
        if (success) {
            return Result.success(true, "用户更新成功");
        } else {
            return Result.error("用户更新失败");
        }
    }

    /**
     * 删除用户
     */
    @ApiOperation("删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataTypeClass = Long.class)
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        boolean exists = userService.getById(id) != null;
        if (!exists) {
            return Result.error("用户不存在", 404);
        }

        boolean success = userService.removeById(id);
        if (success) {
            return Result.success(true, "用户删除成功");
        } else {
            return Result.error("用户删除失败");
        }
    }
}