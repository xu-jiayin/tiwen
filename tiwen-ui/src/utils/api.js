import axios from "axios";
import { Message } from "element-ui";


axios.interceptors.response.use(success => {
    if (success.status || success.status == 200) {
        if (success.data.code == 400) {
            Message.error({ message: success.data.message });
            return;
        }
        Message.success({ message: success.data.message });
    }
}, error => {
    Message.error({ message: "服务器内部错误" });
});

export const postRequest = (url, params) => {
    return axios({
        method: 'post',
        url: url,
        data: params
    })
}