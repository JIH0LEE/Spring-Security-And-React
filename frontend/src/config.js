import { token } from "./utils/cookies";

export const header = {
  headers: {
    Authorization: `${token()}`,
  },
};