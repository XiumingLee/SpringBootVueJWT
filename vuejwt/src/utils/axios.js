import axios from 'axios'

export const Axios = axios.create({
    baseURL:  'http://localhost:8081',
    timeout: 10000,
  })

  //POST传参序列化(添加请求拦截器)
 // 在发送请求之前做某件事
Axios.interceptors.request.use(config => {
    // 设置以 form 表单的形式提交参数，如果以JSON的形式提交表单，可忽略
    if(config.method  === 'post'){
        // JSON 转换为 FormData
        const formData = new FormData()
        Object.keys(config.data).forEach(key => formData.append(key, config.data[key]))
        config.data = formData
    }

    //本案例中将token保存到了localStorage，将其添加到请求头
    if (localStorage.token) {   
        config.headers.Authorization = localStorage.token
    }
    return config
},error =>{
    alert("错误的传参", 'fail')
    return Promise.reject(error)
})