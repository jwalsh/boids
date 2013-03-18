(ns boids.view)

(defn render-boid
  "Renders a boid, complete with placement and rotation"
  [ctx boid]
  (let [[x y] (:pos boid)
        [dx dy] (:vel boid)
        color (:color boid)
        angle (Math/atan2 dy dx)]
    (.save ctx)
    (.translate ctx x y)
    (.rotate ctx angle)
    (.beginPath ctx)
    (set! (.-strokeStyle ctx) color)
    (set! (.-fillStyle ctx) color)
    (set! (.-font ctx) "30pt Calibri")
    (set! (.-textBaseline ctx) "bottom")
    (.fillText ctx (.fromCharCode js/String 9992) -25 25) 
    (.moveTo ctx 5 0) ; tip
    (.lineTo ctx -5 -3) ; corner
    (.lineTo ctx -5 3)  ; corner
    (.lineTo ctx 5 0) ; finish
    (set! (.-lineWidth ctx) 1)
    (.stroke ctx)
    (.restore ctx)))

(defn render
  "Given a collection of boids, render them to the canvas"
  [canvas flock]
  (let [ctx (.getContext canvas "2d")]
    (.clearRect ctx 0 0 (.-width canvas) (.-height canvas))
    (doseq [boid flock]
      (render-boid ctx boid))))

(defn init-mouse
  "Follow the mouse"
  [options-atom]
  ;;  (.console.log js/window options-atom)
  (.on (js/jQuery "body")
       "mousemove"
       (fn [evt]
         ;; (.console.log js/window
         ;;               (.-clientX evt)
         ;;               (.-clientY evt))
         (swap! options-atom assoc :goal [(.-clientX evt)
                                          (.-clientY evt)]))))

(defn log-mouse
  "Log the mouse"
  []
  (.on (js/jQuery "body")
       "mousemove"
       (fn [evt]
         (.console.log js/window [(.-clientX evt)
                                  (.-clientY evt)]))))

(defn init
  "Given an atom containing a seq of boids, initialize the view"
  [flock-atom options-atom]
  (init-mouse options-atom)
  (let [canvas (js/jQuery "<canvas>")]
    (-> canvas
        (.attr "width" (.-innerWidth js/window))
        (.attr "height" (.-innerHeight js/window))
        (.appendTo (js/jQuery "body")))
    (add-watch flock-atom :renderer (fn [_ _ _ flock]
                                      (render (.get canvas 0) flock)))))
