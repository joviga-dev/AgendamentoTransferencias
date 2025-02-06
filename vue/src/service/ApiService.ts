import axios from 'axios'
import VueAxios from "vue-axios";
import type { AxiosResponse } from 'axios'

export class ApiService {

  private static api: string = "http://localhost:8080"

  public static get(
    url: string
  ): Promise<AxiosResponse> {
    return axios.get(`${this.api}${url}`);
  }

  public static post(
    url: string, body: any
  ): Promise<AxiosResponse> {
    return axios.post(`${this.api}${url}`, body);
  }

}
