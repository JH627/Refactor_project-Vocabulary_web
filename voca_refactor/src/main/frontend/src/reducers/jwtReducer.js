import { createSlice } from '@reduxjs/toolkit';

export const TIME_OUT = 600 * 1000;

export const jwtReducer = createSlice({
  name: 'jwtToken',
  initialState: {
    isLogin: false,
    accessToken: null,
    expireTime: null,
  },
  reducers: {
    SET_TOKEN: (state, action) => {
      state.isLogin = true;
      state.accessToken = action.payload;
      state.expireTime = new Date().getTime() + TIME_OUT;
    },
    DELETE_TOKEN: (state) => {
      state.isLogin = false;
      state.accessToken = null;
      state.expireTime = null;
    },
  },
});

export const { SET_TOKEN, DELETE_TOKEN } = jwtReducer.actions;

export default jwtReducer.reducer;
