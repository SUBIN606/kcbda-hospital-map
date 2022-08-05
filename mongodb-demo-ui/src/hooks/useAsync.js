import { useReducer, useCallback, useEffect } from "react";

const initialState = {
  loading: false,
  data: null,
  error: false,
};

const reducer = (state, action) => {
  switch (action.type) {
    case "LOADING":
      return {
        loading: true,
        data: null,
        error: null,
      };

    case "SUCCESS":
      return {
        loading: false,
        data: action.data,
        error: null,
      };

    case "ERROR":
      return {
        loading: false,
        data: null,
        error: action.error,
      };

    case "CLEAR":
      return initialState;

    default:
      return state;
  }
};

const useAsync = (callback, immediate = false) => {
  const [state, dispatch] = useReducer(reducer, initialState);

  const execute = useCallback(
    async (...args) => {
      dispatch({ type: "LOADING" });

      try {
        const data = await callback(...args);
        dispatch({ type: "SUCCESS", data });
        return true;
      } catch (e) {
        dispatch({ type: "ERROR", error: e });
      }
    },
    [callback]
  );

  useEffect(() => {
    immediate && execute();
    return () => dispatch({ type: "CLEAR" });
  }, [immediate, execute]);

  return { ...state, execute };
};

export default useAsync;
