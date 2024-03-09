import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useSelector } from 'react-redux';

import { authInstance } from '../../util/api';

export default function AuthUserInfo() {
  const [userInfo, setUserInfo] = useState({});
  const acToken = useSelector((state) => state.jwt.accessToken);

  useEffect(() => {
    if (acToken) {
      const userInfoUrl = '/auth/user';
      authInstance
        .get(userInfoUrl)
        .then(function (res) {
          setUserInfo(res.data.data);
        })
        .catch(function (e) {
          console.log(e);
        });
    }
  }, [acToken]);

  return (
    <>
      {acToken ? (
        <div>
          <p>logined!</p>
          <p>nickname: {userInfo.nickname}</p>
          <p>level: {userInfo.level}</p>
          <p>wordMax: {userInfo.wordMax}</p>
          <p>
            <Link to="/words">words</Link>
          </p>
        </div>
      ) : (
        <div>
          <p>Auth Please!</p>
          <Link to="/auth">Auth</Link>
        </div>
      )}
    </>
  );
}
