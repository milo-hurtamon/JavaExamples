--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3 (Homebrew)
-- Dumped by pg_dump version 16.3 (Homebrew)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: trg_next_id_on_insert(); Type: FUNCTION; Schema: public; Owner: pgsql_testrole
--

CREATE FUNCTION public.trg_next_id_on_insert() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
begin
new.todoid := nextval('public.todoid_sea');
return new;
end;
$$;


ALTER FUNCTION public.trg_next_id_on_insert() OWNER TO pgsql_testrole;

--
-- Name: todoid_sea; Type: SEQUENCE; Schema: public; Owner: pgsql_testrole
--

CREATE SEQUENCE public.todoid_sea
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 999999999
    CACHE 1;


ALTER SEQUENCE public.todoid_sea OWNER TO pgsql_testrole;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: todolist; Type: TABLE; Schema: public; Owner: pgsql_testrole
--

CREATE TABLE public.todolist (
    todoid integer DEFAULT 0 NOT NULL,
    description text NOT NULL,
    todo_date date NOT NULL,
    completed boolean DEFAULT false,
    observation text
);


ALTER TABLE public.todolist OWNER TO pgsql_testrole;

--
-- Name: todolist todolist_pkey; Type: CONSTRAINT; Schema: public; Owner: pgsql_testrole
--

ALTER TABLE ONLY public.todolist
    ADD CONSTRAINT todolist_pkey PRIMARY KEY (todoid);


--
-- Name: todolist_id_pk; Type: INDEX; Schema: public; Owner: pgsql_testrole
--

CREATE UNIQUE INDEX todolist_id_pk ON public.todolist USING btree (todoid);


--
-- Name: todolist next_id_on_insert; Type: TRIGGER; Schema: public; Owner: pgsql_testrole
--

CREATE TRIGGER next_id_on_insert BEFORE INSERT ON public.todolist FOR EACH ROW EXECUTE FUNCTION public.trg_next_id_on_insert();


--
-- PostgreSQL database dump complete
--

