<template>
  <div class="container">
    <el-form :model="userForm" :rules="rules" ref="userForm" class="login">
      <el-form-item label="学号" prop="userId">
        <el-input v-model="userForm.userId"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="passWord">
        <el-input v-model="userForm.passWord" type="password"></el-input>
      </el-form-item>
      <el-form-item label="Token" prop="token">
        <el-button type="text" @click="dialogVisible = true">如何获取Token?</el-button>

        <el-dialog title="提示" :visible.sync="dialogVisible" width="400px" :before-close="handleClose">
        <span>1.关注微信关注公众号：pushplus推送加</span><br>
        <span>2.复制Token</span><br>
        <span>3.向公众号发送消息：激活消息</span>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
      </el-dialog>

        <el-input v-model="userForm.token"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('userForm')">确定</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { postRequest } from "@/utils/api";
export default {
  data() {
    return {
        dialogVisible: false,
      userForm: {
        userId: "",
        passWord: "",
        token: "",
      },

      rules: {
        userId: [
          { required: true, message: "请输入学号", trigger: "blur" },
          { min: 10, max: 10, message: "必须是10个字符", trigger: "blur" },
        ],
        passWord: [{ required: true, message: "请输入密码", trigger: "blur" }],
        token: [{ required: true, message: "请输入Token", trigger: "blur" }],
      },
    };
  },
  methods: {
    handleClose(done) {
        this.$confirm("确认关闭？")
        .then((_) => {
        done();
        })
        .catch((_) => {});
  },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          postRequest("/tiwen/addUser", this.userForm).then((resp) => {});
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
  },
};
</script>

<style scoped>
.container {
  background-image: url("../../static/images/bg.jpg");
  background-size: cover;
  position: fixed;
  left: 0;
  top: 0;
  bottom: 0;
  right: 0;
}

.login {
  width: 320px;
  margin: 150px auto;
  padding: 50px;
  border: 1px solid #dcdfe6;
  border-radius: 10px;
  box-shadow: 0 0 30px #dcdfe6;
  background: #fff;
}
</style>