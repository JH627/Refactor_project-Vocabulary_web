import { NavLink } from "react-router-dom";
import { useSelector } from "react-redux";

import "./MainNavigation.css";

export default function MainNavigation() {
  const isLogin = useSelector((state) => state.jwt.isLogin);
  return (
    <header>
      <nav>
        <ul className="main-navigation">
          <li>
            <NavLink
              to="/main"
              //   className={({ isActive }) =>
              //     isActive ? classes.active : undefined
              //   }
            >
              Main
            </NavLink>
          </li>
          <li>
            <NavLink
              to="/words"
              //   className={({ isActive }) =>
              //     isActive ? classes.active : undefined
              //   }
            >
              Words
            </NavLink>
          </li>
          {!isLogin ? (
            <li>
              <NavLink
                to="/auth"
                // className={({ isActive }) =>
                //   isActive ? classes.active : undefined
                // }
              >
                Auth
              </NavLink>
            </li>
          ) : (
            <li>
              <NavLink
                to="/logout"
                // className={({ isActive }) =>
                //   isActive ? classes.active : undefined
                // }
              >
                logout
              </NavLink>
            </li>
          )}
        </ul>
      </nav>
    </header>
  );
}
