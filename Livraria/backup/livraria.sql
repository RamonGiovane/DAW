

CREATE TABLE public.categoria (
    codigo integer NOT NULL,
    descricao character varying(50)
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 50350)
-- Name: categoria_codigo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.categoria_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.categoria_codigo_seq OWNER TO postgres;

--
-- TOC entry 2177 (class 0 OID 0)
-- Dependencies: 187
-- Name: categoria_codigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.categoria_codigo_seq OWNED BY public.categoria.codigo;


--
-- TOC entry 193 (class 1259 OID 50385)
-- Name: item_pedido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_pedido (
    codigo_pedido integer NOT NULL,
    codigo_livro integer NOT NULL,
    quantidade integer
);


ALTER TABLE public.item_pedido OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 50360)
-- Name: livro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.livro (
    codigo integer NOT NULL,
    titulo character varying(100),
    descricao character varying(100),
    autor character varying(100),
    codcategoria integer,
    preco double precision,
    estoque integer
);


ALTER TABLE public.livro OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 50358)
-- Name: livro_codigo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.livro_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.livro_codigo_seq OWNER TO postgres;

--
-- TOC entry 2178 (class 0 OID 0)
-- Dependencies: 189
-- Name: livro_codigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.livro_codigo_seq OWNED BY public.livro.codigo;


--
-- TOC entry 192 (class 1259 OID 50374)
-- Name: pedido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pedido (
    codigo integer NOT NULL,
    cliente integer,
    valor double precision
);


ALTER TABLE public.pedido OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 50372)
-- Name: pedido_codigo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pedido_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pedido_codigo_seq OWNER TO postgres;

--
-- TOC entry 2179 (class 0 OID 0)
-- Dependencies: 191
-- Name: pedido_codigo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pedido_codigo_seq OWNED BY public.pedido.codigo;


--
-- TOC entry 186 (class 1259 OID 50344)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id integer NOT NULL,
    login character varying(20) NOT NULL,
    senha character varying(20),
    tipo character varying(20)
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 50342)
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_seq OWNER TO postgres;

--
-- TOC entry 2180 (class 0 OID 0)
-- Dependencies: 185
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_id_seq OWNED BY public.usuario.id;


--
-- TOC entry 2024 (class 2604 OID 50355)
-- Name: categoria codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria ALTER COLUMN codigo SET DEFAULT nextval('public.categoria_codigo_seq'::regclass);


--
-- TOC entry 2025 (class 2604 OID 50363)
-- Name: livro codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livro ALTER COLUMN codigo SET DEFAULT nextval('public.livro_codigo_seq'::regclass);


--
-- TOC entry 2026 (class 2604 OID 50377)
-- Name: pedido codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido ALTER COLUMN codigo SET DEFAULT nextval('public.pedido_codigo_seq'::regclass);


--
-- TOC entry 2023 (class 2604 OID 50347)
-- Name: usuario id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);




--
-- TOC entry 2181 (class 0 OID 0)
-- Dependencies: 187
-- Name: categoria_codigo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categoria_codigo_seq', 12, true);


--
-- TOC entry 2168 (class 0 OID 50385)
-- Dependencies: 193
-- Data for Name: item_pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--


--
-- TOC entry 2182 (class 0 OID 0)
-- Dependencies: 189
-- Name: livro_codigo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.livro_codigo_seq', 15, true);


--
-- TOC entry 2167 (class 0 OID 50374)
-- Dependencies: 192
-- Data for Name: pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--


--
-- TOC entry 2183 (class 0 OID 0)
-- Dependencies: 191
-- Name: pedido_codigo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pedido_codigo_seq', 16, true);




--
-- TOC entry 2184 (class 0 OID 0)
-- Dependencies: 185
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_id_seq', 8, true);


--
-- TOC entry 2038 (class 2606 OID 50389)
-- Name: item_pedido item_pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_pedido
    ADD CONSTRAINT item_pedido_pkey PRIMARY KEY (codigo_pedido, codigo_livro);


--
-- TOC entry 2036 (class 2606 OID 50379)
-- Name: pedido pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (codigo);


--
-- TOC entry 2032 (class 2606 OID 50357)
-- Name: categoria pk-categoria; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT "pk-categoria" PRIMARY KEY (codigo);


--
-- TOC entry 2034 (class 2606 OID 50365)
-- Name: livro pk-livro; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livro
    ADD CONSTRAINT "pk-livro" PRIMARY KEY (codigo);


--
-- TOC entry 2028 (class 2606 OID 50423)
-- Name: usuario usuario_login_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_login_key UNIQUE (login);


--
-- TOC entry 2030 (class 2606 OID 50421)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 2039 (class 2606 OID 50366)
-- Name: livro fk-livro-categoria; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.livro
    ADD CONSTRAINT "fk-livro-categoria" FOREIGN KEY (codcategoria) REFERENCES public.categoria(codigo);


--
-- TOC entry 2041 (class 2606 OID 50390)
-- Name: item_pedido item_pedido_codigo_livro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_pedido
    ADD CONSTRAINT item_pedido_codigo_livro_fkey FOREIGN KEY (codigo_livro) REFERENCES public.livro(codigo);


--
-- TOC entry 2042 (class 2606 OID 50395)
-- Name: item_pedido item_pedido_codigo_pedido_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_pedido
    ADD CONSTRAINT item_pedido_codigo_pedido_fkey FOREIGN KEY (codigo_pedido) REFERENCES public.pedido(codigo);


--
-- TOC entry 2040 (class 2606 OID 50429)
-- Name: pedido pedido_cliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_cliente_fkey FOREIGN KEY (cliente) REFERENCES public.usuario(id);


-- Completed on 2019-10-28 01:23:38

--
-- PostgreSQL database dump complete
--

